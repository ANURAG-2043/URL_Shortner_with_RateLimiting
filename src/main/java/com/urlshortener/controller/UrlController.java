package com.urlshortener.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.urlshortener.dto.UrlRequestDTO;
import com.urlshortener.dto.UrlResponseDTO;
import com.urlshortener.servic.UrlService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UrlController {

    @Autowired
    private UrlService service;
    
    
    // API to create short URL
    @PostMapping("/shorten")
    public UrlResponseDTO shorten(@RequestBody UrlRequestDTO request) {

        String shortUrl = service.shortenUrl(request.getUrl());

        return new UrlResponseDTO(shortUrl);
    }
    
 // redirect endpoint
  @GetMapping("/{shortCode:[a-zA-Z0-9]+}")
  public void redirect(@PathVariable String shortCode,
                       HttpServletResponse response) throws Exception {

      String originalUrl = service.getOriginalUrl(shortCode);

      if (originalUrl != null) {
          response.sendRedirect(originalUrl);
      } else {
          response.sendError(HttpServletResponse.SC_NOT_FOUND, "Short URL not found");
      }
  }

//    // redirect endpoint
//    @GetMapping("/{shortCode}")
//    public void redirectToOriginalUrl(@PathVariable String shortCode, HttpServletResponse response) throws IOException {
//        // 1. Fetch the original URL from your database using the shortCode
//    	System.out.println("Successfully saved! ShortCode: " + model.getShortCode() + " -> URL: " + UrlMapping.getOriginalUrl());
//    	String originalUrl = service.getOriginalUrl(shortCode); 
//
//        if (originalUrl == null) {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Short URL not found");
//            return;
//        }
//
//        // 2. The Fix: Ensure the URL has a protocol
//        if (!originalUrl.startsWith("http://") && !originalUrl.startsWith("https://")) {
//            originalUrl = "https://" + originalUrl;
//        }
//
//        // 3. Perform the redirect
//        response.sendRedirect(originalUrl);
//    }
}