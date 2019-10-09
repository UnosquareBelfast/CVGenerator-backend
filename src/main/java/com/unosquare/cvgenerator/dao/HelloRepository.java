package com.unosquare.cvgenerator.dao;

import com.unosquare.cvgenerator.entity.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloRepository extends JpaRepository<Greeting, Integer> {
}
