package com.unosquare.cvgenerator.controller;

import com.unosquare.cvgenerator.exception.IdNotFoundException;
import com.unosquare.cvgenerator.model.dto.TemplateDetailDTO;
import com.unosquare.cvgenerator.service.CvService;
import com.unosquare.cvgenerator.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PdfController {

    private final TemplateService templateService;
    private final CvService cvService;

    @GetMapping("/generatepdf")
    public ResponseEntity<String> generatePdf(@RequestParam int userId, @RequestParam int templateId) {

        TemplateDetailDTO templateDetailDTO = templateService.findById(templateId)
                .orElseThrow(() -> new IdNotFoundException(String.format("Template ID not found: %s", templateId)));
        String result = templateDetailDTO.getTemplateHtml();

        return ResponseEntity.ok()
                .header("Content-Type", "text/html")
                .body(cvService.getParsedHtml(result));
    }

}
