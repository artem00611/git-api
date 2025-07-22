# GitHub API Integration

This is a Spring Boot application that integrates with the GitHub REST API.  
It retrieves all **non-forked repositories** for a given GitHub username, including their **branches** and the **latest commit SHA** of each branch.



## Technologies Used

- **Java 21**
- **Spring Boot 3.5**
- **Spring Web (RestTemplate)**
- **JUnit 5 + Spring Boot Test**
- **GitHub REST API v3**



##  Getting Started

### Prerequisites

- **Java 17 or higher**
- **Maven 3.6+**

### Clone and Run

```bash
git clone https://github.com/your-username/github-api.git
cd github-api
./mvnw spring-boot:run
```
Or run `GitHubApiApplication.java` from your IDE.

The app will be available at:
`http://localhost:8080`


## API Usage

### Get Repositories by GitHub Username

```http
GET /api/v1/github/{username}
```

### Example
```http
GET http://localhost:8080/api/v1/github/artem00611
```

### Sample Response

```json
[
  {
    "repositoryName": "local-news",
    "ownerLogin": "artem00611",
    "branches": [
      {
        "branchName": "main",
        "lastCommitSha": "c6d83f8..."
      }
    ]
  }
]
```


## Error Handling

If the GitHub user is not found, the API returns:
```json
{
  "status": 404,
  "message": "GitHub user not found"
}
```

## Running Tests

To run all tests:
```bash
./mvnw test
```

## Author

__Artem Senyshyn__

GitHub: [artem00611](https://github.com/artem00611)

LinkedIn: [Artem Senyshyn](https://www.linkedin.com/in/artem-senyshyn%7F-42a61718a/)
