package com.example.githubapi.controller;

import com.example.githubapi.dto.RepositoryResponse;
import com.example.githubapi.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/github")
public class GitHubController {
    private final GitHubService gitHubService;

    @Autowired
    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<RepositoryResponse>> getUserRepositories(@PathVariable String username){
        return ResponseEntity.status(HttpStatus.OK)
                .body(gitHubService.getRepositoriesByUser(username));
    }
}
