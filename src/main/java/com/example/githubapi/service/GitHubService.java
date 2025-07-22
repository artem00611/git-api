package com.example.githubapi.service;

import com.example.githubapi.client.GitHubClient;
import com.example.githubapi.dto.BranchResponse;
import com.example.githubapi.dto.RepositoryResponse;
import com.example.githubapi.dto.external.GitHubRepoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GitHubService {

    private final GitHubClient gitHubClient;

    @Autowired
    public GitHubService(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    public List<RepositoryResponse> getRepositoriesByUser(String username) {
        List<RepositoryResponse> result = new ArrayList<>();
        List<GitHubRepoDto> gitHubRepoDtos = gitHubClient.getUserRepositories(username);

        for (GitHubRepoDto repo : gitHubRepoDtos) {
            if (!repo.fork()) {
                List<BranchResponse> branches = gitHubClient.getBranches(username, repo.name())
                        .stream()
                        .map(branch -> new BranchResponse(branch.branchName(), branch.commitInfo().sha()))
                        .toList();

                result.add(new RepositoryResponse(
                        repo.name(),
                        repo.owner().getLogin(),
                        branches
                ));
            }
        }

        return result;
    }
}
