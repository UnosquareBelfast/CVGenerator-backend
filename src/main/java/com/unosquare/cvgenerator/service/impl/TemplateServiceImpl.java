package com.unosquare.cvgenerator.service.impl;

import com.unosquare.cvgenerator.dao.TemplateRepository;
import com.unosquare.cvgenerator.model.dto.TemplateDetailDTO;
import com.unosquare.cvgenerator.model.entity.TemplateDetail;
import com.unosquare.cvgenerator.service.TemplateService;
import com.unosquare.cvgenerator.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository templateRepository;
    private final MapperUtil mapperUtil;

    @Override
    public List<TemplateDetailDTO> findAll() {
        List<TemplateDetail> result = templateRepository.findAll();
        return mapperUtil.map(result, TemplateDetailDTO.class);
    }

    @Override
    public Optional<TemplateDetailDTO> findById(Integer id) {
        return templateRepository.findById(id)
                .map(template -> mapperUtil.map(template, TemplateDetailDTO.class));
    }

}
