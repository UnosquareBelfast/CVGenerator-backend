package com.unosquare.cvgenerator.service.impl;

import com.unosquare.cvgenerator.service.CvService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class CvServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    private CvService cvService;

    @Before
    public void setup() {
        cvService = new CvServiceImpl(restTemplate);
    }

    // TODO: Mockito.when() call needs fixed
    @Test
    public void getParsedHtml_whenHtmlPassed_parsedHtmlReturned() {
        // arrange
//        Map<String, Object> map = new HashMap<>();
//        map.put("firstName", "Bob");
//
//        when(restTemplate.exchange(anyString(),
//                any(),
//                any(),
//                ArgumentMatchers.<ParameterizedTypeReference<Map<String, Object>>>any()))
//                .thenReturn(new ResponseEntity<>(map, HttpStatus.OK));
//
//        String html = "<html>~~TOP_LEVEL_START~~<body>~~firstName~~</body>~~TOP_LEVEL_END~~</html>";
//
//        // act
//        String result = cvService.getParsedHtml(html);
//
//        // assert
//        Assert.assertNotNull(result);
    }

}
