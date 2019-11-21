package com.unosquare.cvgenerator.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TemplateDetailDTO {

    private Integer id;
    private String templateName;
    private String templateHtml;

}
