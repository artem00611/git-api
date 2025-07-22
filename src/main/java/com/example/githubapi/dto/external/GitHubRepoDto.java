package com.example.githubapi.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public record GitHubRepoDto(
        @JsonProperty("name") String name,
        @JsonProperty("owner") Owner owner,
        @JsonProperty("fork") Boolean fork) {
    @Data
    public static class Owner {
        @JsonProperty("login") private String login;
    }
}
