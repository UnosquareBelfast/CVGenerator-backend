package com.unosquare.cvgenerator.controller;

import com.flextrade.jfixture.JFixture;
import com.unosquare.cvgenerator.AbstractWebIntegrationTest;
import com.unosquare.cvgenerator.dao.TemplateRepository;
import com.unosquare.cvgenerator.model.entity.TemplateDetail;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.containsString;

public class PdfControllerIntegrationTest extends AbstractWebIntegrationTest {

    @Autowired
    private TemplateRepository repository;

    private TemplateDetail templateDetail;


    @Before
    public void setUp() {
        JFixture fixture = new JFixture();
        templateDetail = fixture.create(TemplateDetail.class);
        templateDetail.setTemplateHtml("<html>~~TOP_LEVEL_START~~<body>~~firstName~~</body>~~TOP_LEVEL_END~~</html>");
        templateDetail = repository.save(templateDetail);

        String json = "{\"firstName\":\"Bob\"}";

        wireMockRule.stubFor(get(urlEqualTo("/cvdetails"))
                .willReturn(okJson(json)));
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void generatePDF_whenValidTemplateIdPassed_returnsParsedHtml() {
        Map<String, Integer> queryParams = new HashMap<>();
        queryParams.put("userId", 0);
        queryParams.put("templateId", templateDetail.getId());

        RestAssured.registerParser("text/plain", Parser.HTML);
        RestAssured.given()
                .queryParams(queryParams)
                .when()
                .get("/generatepdf")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("html.body", containsString("Bob"));
    }

    @Test
    public void generatePDF_whenInvalidTemplateIdPassed_returnNoContentStatusCode() {
        Map<String, Integer> queryParams = new HashMap<>();
        queryParams.put("userId", 0);
        queryParams.put("templateId", templateDetail.getId()-1);

        RestAssured.given()
                .queryParams(queryParams)
                .when()
                .get("/generatepdf")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
