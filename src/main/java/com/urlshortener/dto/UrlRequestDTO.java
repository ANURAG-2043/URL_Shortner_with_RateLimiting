package com.urlshortener.dto;

import jakarta.validation.constraints.NotBlank;

/*
 * DTO class used for receiving request from client
 * Example JSON:
 * {
 *   "url": "https://google.com"
 * }
 */

public class UrlRequestDTO {

    // original URL from user
    @NotBlank(message = "URL cannot be empty")
    private String url;

    // getter
    public String getUrl() {
        return url;
    }

    // setter
    public void setUrl(String url) {
        this.url = url;
    }
}