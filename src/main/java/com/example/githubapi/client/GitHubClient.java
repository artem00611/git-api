package com.example.githubapi.client;

import com.example.githubapi.dto.external.GitHubBranchResponse;
import com.example.githubapi.dto.external.GitHubRepoDto;
import com.example.githubapi.exception.GitHubUserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class GitHubClient {
    private final RestTemplate restTemplate;
    private static final String BASE_URL = "https://api.github.com";

    @Autowired
    public GitHubClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<GitHubRepoDto> getUserRepositories(String username){
        try {
            String url = BASE_URL + "/users/{username}/repos";

            HttpEntity<Void> request = new HttpEntity<>(new HttpHeaders());

            ResponseEntity<GitHubRepoDto[]> response = restTemplate.exchange(url, HttpMethod.GET, request, GitHubRepoDto[].class, username);
            System.out.println(Arrays.asList(response.getBody()));
            return Arrays.asList(response.getBody());
        } catch (HttpClientErrorException.NotFound ex){
            throw new GitHubUserNotFoundException("GitHub user not found!");
        }
    }

    public List<GitHubBranchResponse> getBranches(String owner, String repo){
        String url = BASE_URL + "/repos/{owner}/{repo}/branches";
        ResponseEntity<GitHubBranchResponse[]> response = restTemplate.getForEntity(url, GitHubBranchResponse[].class,owner,repo);
        System.out.println(Arrays.asList(response.getBody()));
        return Arrays.asList(response.getBody());
    }
}
