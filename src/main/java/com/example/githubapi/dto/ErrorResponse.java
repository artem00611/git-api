package com.example.githubapi.dto;

public record ErrorResponse(
    Integer status,
    String message
) {
}
