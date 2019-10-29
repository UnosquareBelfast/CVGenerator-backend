package com.unosquare.cvgenerator;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * This abstract class should be extended by integration tests
 * that do not require a controller e.g. service/repository tests.
 */
@Transactional
@SpringBootTest
public abstract class AbstractIntegrationTest {
}
