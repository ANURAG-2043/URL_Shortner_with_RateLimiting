package com.urlshortener.dto;

/*
 * DTO class used to send response to client
 * Example response:
 * {
 *   "shortUrl": "http://localhost:8080/aB21x"
 * }
 */

public class UrlResponseDTO {

    private String shortUrl;

    // constructor
    public UrlResponseDTO(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    // getter
    public String getShortUrl() {
        return shortUrl;
    }

    // setter
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}