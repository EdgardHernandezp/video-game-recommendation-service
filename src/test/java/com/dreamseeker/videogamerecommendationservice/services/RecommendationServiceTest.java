package com.dreamseeker.videogamerecommendationservice.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.dreamseeker.videogamerecommendationservice.domains.RecommendationResponse;
import com.dreamseeker.videogamerecommendationservice.domains.UserInfo;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RecommendationServiceTest {

    private final RecommendationService recommendationService = new RecommendationService();

    @Test
    void fetchRecommendationsWithoutExclusions() {
        UserInfo userInfo = new UserInfo(UUID.randomUUID().toString(), List.of("Adventure", "Soul like", "Horror"));
        RecommendationResponse recommendationResponse = recommendationService.fetchRecommendations(userInfo, Optional.of("Horror"));

        assertThat(recommendationResponse).isNotNull();
        assertThat(recommendationResponse.videogames()).isNotNull().hasSize(1);
        assertThat(recommendationResponse.videogames().get(0).name()).isEqualTo("Red Dead Redemption");
    }

    @Test
    void fetchRecommendationsWithExclusions() {
        UserInfo userInfo = new UserInfo(UUID.randomUUID().toString(), List.of("Adventure", "Platformer", "Horror"));
        RecommendationResponse recommendationResponse = recommendationService.fetchRecommendations(userInfo, Optional.of("Horror,Platformer"));

        assertThat(recommendationResponse).isNotNull();
        assertThat(recommendationResponse.videogames()).isNotNull().hasSize(1);
        assertThat(recommendationResponse.videogames().get(0).name()).isEqualTo("Red Dead Redemption");
    }
}