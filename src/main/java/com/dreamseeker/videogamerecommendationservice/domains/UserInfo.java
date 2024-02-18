package com.dreamseeker.videogamerecommendationservice.domains;

import java.util.List;

public record UserInfo(String userId, List<String> preferredGenres) {
}
