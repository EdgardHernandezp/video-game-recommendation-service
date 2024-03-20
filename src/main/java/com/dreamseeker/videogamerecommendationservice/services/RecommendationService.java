package com.dreamseeker.videogamerecommendationservice.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    public RecommendationResponse fetchRecommendations(UserInfo userInfo, Optional<String> exclusions) {
        String[] splitExclusions = exclusions.map(ex -> ex.split(",")).orElse(new String[0]);
        List<Videogame> filteredGames = videogamesRepo.stream()
                .filter(videogame -> Arrays.stream(splitExclusions).noneMatch(excludedGenre -> excludedGenre.equals(videogame.genre())))
                .filter(videogame -> userInfo.preferredGenres().contains(videogame.genre()))
                .toList();
        return new RecommendationResponse(filteredGames);
    }
}
