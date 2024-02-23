package com.dreamseeker.videogamerecommendationservice;

import com.dreamseeker.videogamerecommendationservice.controllers.RecommendationController;
import com.dreamseeker.videogamerecommendationservice.services.RecommendationService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;

public class BaseTestClass {
    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.standaloneSetup(new RecommendationController(new RecommendationService()));
    }
}
