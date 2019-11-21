package com.unosquare.cvgenerator.controller;

import com.unosquare.cvgenerator.model.view.TemplateDetailView;
import com.unosquare.cvgenerator.service.TemplateService;
import com.unosquare.cvgenerator.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/templates")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<TemplateDetailView>> getTemplates() {
        List<TemplateDetailView> resultListView = mapperUtil.map(templateService.findAll(), TemplateDetailView.class);
        if (resultListView.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resultListView);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemplateDetailView> getTemplate(@PathVariable("id") Integer id) {
        return templateService.findById(id)
                .map(result -> mapperUtil.map(result, TemplateDetailView.class))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

}
