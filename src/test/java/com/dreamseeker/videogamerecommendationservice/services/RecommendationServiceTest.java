package com.dreamseeker.videogamerecommendationservice.services;

import java.util.List;
import java.util.UUID;

import com.dreamseeker.videogamerecommendationservice.domains.RecommendationResponse;
import com.dreamseeker.videogamerecommendationservice.domains.UserInfo;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RecommendationServiceTest {

    private final RecommendationService recommendationService = new RecommendationService();

    @Test
    void fetchRecommendations() {
        UserInfo userInfo = new UserInfo(UUID.randomUUID().toString(), List.of("Adventure", "Soul like", "Horror"));
        RecommendationResponse recommendationResponse = recommendationService.fetchRecommendations(userInfo);

        assertThat(recommendationResponse).isNotNull();
        assertThat(recommendationResponse.videogames()).isNotNull().hasSize(1);
        assertThat(recommendationResponse.videogames().get(0).name()).isEqualTo("Red Dead Redemption");
    }
}