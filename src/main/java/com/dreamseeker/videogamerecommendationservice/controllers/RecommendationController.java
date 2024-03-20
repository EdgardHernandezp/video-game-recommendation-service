package com.dreamseeker.videogamerecommendationservice.controllers;

import java.util.Optional;

import com.dreamseeker.videogamerecommendationservice.domains.RecommendationResponse;
import com.dreamseeker.videogamerecommendationservice.domains.UserInfo;
import com.dreamseeker.videogamerecommendationservice.services.RecommendationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RecommendationController {

    private final RecommendationService service;

    @PutMapping("/fetch-recommendations")
    public ResponseEntity<RecommendationResponse> fetchRecommendations(@RequestBody UserInfo userInfo, @RequestParam(required = false) Optional<String> exclusions) {
        return ResponseEntity.ok(service.fetchRecommendations(userInfo, exclusions));
    }
}
