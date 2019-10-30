package com.unosquare.cvgenerator.service.impl;

import com.flextrade.jfixture.JFixture;
import com.unosquare.cvgenerator.dao.TemplateRepository;
import com.unosquare.cvgenerator.model.dto.TemplateDetailDTO;
import com.unosquare.cvgenerator.model.entity.TemplateDetail;
import com.unosquare.cvgenerator.service.TemplateService;
import com.unosquare.cvgenerator.util.MapperUtil;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TemplateServiceImplTest {

    @Mock
    private MapperUtil mapperUtil;

    @Mock
    private TemplateRepository repository;

    private TemplateService templateService;

    private JFixture fixture = new JFixture();


    @Before
    public void setup() {
        templateService = new TemplateServiceImpl(repository, mapperUtil);
    }

    @Test
    public void findAll_whenDataAvailable_returnsDtoList() {
        // arrange
        List<TemplateDetail> testListTemplateDetail =
                Lists.newArrayList(fixture.collections().createCollection(TemplateDetail.class));
        fixture.collections().addManyTo(testListTemplateDetail, TemplateDetail.class);
        when(repository.findAll()).thenReturn(testListTemplateDetail);
        List<TemplateDetailDTO> testListTemplateDetailDTO =
                mapperUtil.map(testListTemplateDetail, TemplateDetailDTO.class);

        // act
        List<TemplateDetailDTO> actualListTemplateDetailDTO = templateService.findAll();

        // assert
        Assert.assertEquals(testListTemplateDetailDTO, actualListTemplateDetailDTO);
        Mockito.verify(repository, times(1)).findAll();
    }

    @Test
    public void findAll_whenNoData_returnsEmptyList() {
        // arrange
        when(repository.findAll()).thenReturn(emptyList());

        // act
        List<TemplateDetailDTO> actualListTemplateDetailDTO = templateService.findAll();

        // assert
        Assert.assertTrue(actualListTemplateDetailDTO.isEmpty());
    }

    @Test
    public void findById_whenValidIdPassed_returnsDto() {
        // arrange
        TemplateDetail testTemplateDetail = fixture.create(TemplateDetail.class);
        when(repository.findById(testTemplateDetail.getId())).thenReturn(Optional.of(testTemplateDetail));
        TemplateDetailDTO testTemplateDetailDTO = mapperUtil.map(testTemplateDetail, TemplateDetailDTO.class);

        // act
        TemplateDetailDTO actualTemplateDetailDTO =
                templateService.findById(testTemplateDetail.getId()).orElse(null);

        // assert
        Assert.assertEquals(testTemplateDetailDTO, actualTemplateDetailDTO);
        Mockito.verify(repository, times(1)).findById(testTemplateDetail.getId());
    }

    @Test
    public void findById_whenInvalidIdPassed_emptyOptionalReturned() {
        // arrange
        TemplateDetailDTO testTemplateDetailDTO = fixture.create(TemplateDetailDTO.class);
        when(repository.findById(testTemplateDetailDTO.getId())).thenReturn(Optional.empty());

        // act
        Optional<TemplateDetailDTO> result = templateService.findById(testTemplateDetailDTO.getId());

        // assert
        Assert.assertFalse(result.isPresent());
    }

}
