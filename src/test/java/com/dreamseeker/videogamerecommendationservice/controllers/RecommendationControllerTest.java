package com.dreamseeker.videogamerecommendationservice.controllers;

import java.util.List;
import java.util.UUID;

import com.dreamseeker.videogamerecommendationservice.domains.RecommendationResponse;
import com.dreamseeker.videogamerecommendationservice.domains.UserInfo;
import com.dreamseeker.videogamerecommendationservice.domains.Videogame;
import com.dreamseeker.videogamerecommendationservice.services.RecommendationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class RecommendationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecommendationService recommendationService;

    @Test
    void fetchRecommendations() throws Exception {
        Videogame v1 = new Videogame("Red Dead Redemption", "Adventure");
        Videogame v2 = new Videogame("Crash", "Platformer");
        List<Videogame> videogames = List.of(v1, v2);
        when(recommendationService.fetchRecommendations(any())).thenReturn(new RecommendationResponse(videogames));

        ObjectMapper objectMapper = new ObjectMapper();
        String serializedUserInfo = objectMapper.writeValueAsString(new UserInfo(UUID.randomUUID().toString(), List.of("Adventure", "Platformer")));
        mockMvc.perform(put("/fetch-recommendations").content(serializedUserInfo).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".videogames.[*].name", containsInAnyOrder("Red Dead Redemption", "Crash")));
    }
}