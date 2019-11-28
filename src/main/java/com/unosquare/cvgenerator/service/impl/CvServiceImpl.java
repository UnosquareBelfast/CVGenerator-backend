package com.unosquare.cvgenerator.service.impl;

import com.unosquare.cvgenerator.model.entity.CvConstants;
import com.unosquare.cvgenerator.service.CvService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class CvServiceImpl implements CvService {

    @Value("${cv.resource.url}")
    private String resourceUrl;

    private final RestTemplate restTemplate;

    @Override
    public String getParsedHtml(String templateHtml) {

        Map<String, Object> tokensMap = getTokensMap();

        // parse top level tokens
        templateHtml = parseTopLevel(templateHtml, tokensMap);

        // parse qualifications tokens
        templateHtml = parseLevel(
                templateHtml,
                tokensMap,
                CvConstants.QUALIFICATIONS,
                CvConstants.QUAL_START_TAG,
                CvConstants.QUAL_END_TAG);

        // parse work tokens
        templateHtml = parseLevel(
                templateHtml,
                tokensMap,
                CvConstants.WORK,
                CvConstants.WORK_START_TAG,
                CvConstants.WORK_END_TAG);

        // clean up leftover tokens
        templateHtml = cleanUpTokens(templateHtml);

        generatePDF(templateHtml);

        return templateHtml;
    }

    private String parseTopLevel(String templateHtml, Map<String, Object> tokensMap) {
        // find the html block to be parsed
        Pattern pattern = Pattern.compile(CvConstants.TOP_START_TAG + "(?s)(.*?)" + CvConstants.TOP_END_TAG);
        Matcher matcher = pattern.matcher(templateHtml);
        String result = "";
        while (matcher.find()) {
            result = matcher.group(1);
        }

        // parse html block, replacing terms
        result = replaceTokens(result, tokensMap);

        // replace the original html block with the parsed html
        templateHtml = matcher.replaceAll(result);

        return templateHtml;
    }

    private String parseLevel(String templateHtml, Map<String, Object> tokensMap, String level, String startTag, String endTag) {
        // find the html block to be parsed
        Pattern pattern = Pattern.compile(startTag + "(?s)(.*?)" + endTag);
        Matcher matcher = pattern.matcher(templateHtml);
        String result = null;
        while (matcher.find()) {
            result = matcher.group(1);
        }

        // parse html block, replacing terms
        StringBuilder sb = new StringBuilder();
        Object resultOpt = Optional.ofNullable(tokensMap.get(level)).orElse(Collections.emptyList());
        for (Map<String, Object> currentMap : (List<Map<String, Object>>) resultOpt) {
            sb.insert(0, replaceTokens(result, currentMap));
        }

        // replace the original html block with the parsed html
        templateHtml = matcher.replaceAll(sb.toString());

        return templateHtml;
    }

    private String replaceTokens(String input, Map<String, Object> tokensMap) {
        String patternString = "~~(" + StringUtils.join(tokensMap.keySet(), "|") + ")~~";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(input);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, tokensMap.get(matcher.group(1)).toString());
        }

        return matcher.appendTail(sb).toString();
    }

    private String cleanUpTokens(String templateHtml) {
        String patternString = "~~(" + "(?s)(.*?)" + ")~~";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(templateHtml);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "");
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    private Map<String, Object> getTokensMap() {
        ResponseEntity<Map<String, Object>> responseEntity;
        ParameterizedTypeReference<Map<String, Object>> typeRef =
                new ParameterizedTypeReference<Map<String, Object>>() {};
        responseEntity = restTemplate.exchange(resourceUrl, HttpMethod.GET, null, typeRef);

        return responseEntity.getBody();
    }

    private void generatePDF(String html) {
        try {
            Document doc = Jsoup.parse(html);
            doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);

            OutputStream outputStream = new FileOutputStream("./generatedpdf/generated_cv.pdf");
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(doc.html());
            renderer.layout();
            renderer.createPDF(outputStream);
            outputStream.close();

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
