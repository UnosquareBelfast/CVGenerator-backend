package com.unosquare.cvgenerator.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "templates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "template_name")
    private String templateName;

    @Column(name = "template_html")
    private String templateHtml;
}
