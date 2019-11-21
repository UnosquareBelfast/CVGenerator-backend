package com.unosquare.cvgenerator.dao;

import com.unosquare.cvgenerator.model.entity.TemplateDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends JpaRepository<TemplateDetail, Integer> {
}
