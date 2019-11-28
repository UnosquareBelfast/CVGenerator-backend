package com.unosquare.cvgenerator;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Base class to derive concrete web test classes from.
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractWebIntegrationTest {

    @LocalServerPort
    private int port;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8082);

    @Before
    public void setUpBase() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setPort(port)
                .log(LogDetail.ALL)
                .build();
    }
}
