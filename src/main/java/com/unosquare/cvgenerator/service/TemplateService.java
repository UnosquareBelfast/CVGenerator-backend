package com.unosquare.cvgenerator.service;

import com.unosquare.cvgenerator.model.dto.TemplateDetailDTO;

import java.util.List;
import java.util.Optional;

public interface TemplateService {

    List<TemplateDetailDTO> findAll();
    Optional<TemplateDetailDTO> findById(Integer id);

}
