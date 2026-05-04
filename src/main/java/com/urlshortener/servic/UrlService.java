package com.urlshortener.servic;

public interface UrlService {

    String shortenUrl(String originalUrl);

    String getOriginalUrl(String shortCode);
}