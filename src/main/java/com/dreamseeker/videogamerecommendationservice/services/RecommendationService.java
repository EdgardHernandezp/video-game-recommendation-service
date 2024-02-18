package com.dreamseeker.videogamerecommendationservice.services;

import java.util.List;

import com.dreamseeker.videogamerecommendationservice.domains.RecommendationResponse;
import com.dreamseeker.videogamerecommendationservice.domains.UserInfo;
import com.dreamseeker.videogamerecommendationservice.domains.Videogame;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    private static final List<Videogame> videogamesRepo = List.of(
            new Videogame("Red Dead Redemption", "Adventure"),
            new Videogame("The Witcher 3", "RPG"),
            new Videogame("Crash Bandicoot 3", "Platformer"));

    public RecommendationResponse fetchRecommendations(UserInfo userInfo) {
        List<Videogame> filteredGames = videogamesRepo.stream().filter(videogame -> userInfo.preferredGenres().contains(videogame.genre())).toList();
        return new RecommendationResponse(filteredGames);
    }
}
