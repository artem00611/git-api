package com.example.githubapi.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GitHubBranchResponse(
        @JsonProperty("name") String branchName,
        @JsonProperty("commit") CommitInfo commitInfo
) {
    public record CommitInfo(
            @JsonProperty("sha") String sha
    ) {}
}