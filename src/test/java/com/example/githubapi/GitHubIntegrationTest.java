package com.example.githubapi;

import com.example.githubapi.dto.RepositoryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GitHubIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnRepositoriesWithBranchesAndCommits(){
        String username = "artem00611";

        String url = "http://localhost:" + port + "/api/v1/github/" + username;
        RepositoryResponse[] responses = restTemplate.getForObject(url, RepositoryResponse[].class);

        assertThat(responses).isNotNull();
        assertThat(responses.length).isGreaterThan(0);

        for (RepositoryResponse response : responses){
            assertThat(response.getRepositoryName()).isNotBlank();
            assertThat(response.getOwnerLogin()).isEqualTo(username);
            assertThat(response.getBranches()).isNotEmpty();

            response.getBranches().forEach(branch -> {
                assertThat(branch.getBranchName()).isNotBlank();
                assertThat(branch.getLastCommitSha()).isNotBlank();
            });
        }
    }
}
