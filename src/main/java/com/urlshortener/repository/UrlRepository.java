package com.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.urlshortener.model.UrlMapping;

public interface UrlRepository extends JpaRepository<UrlMapping, Long> {

    // find URL by short code
    UrlMapping findByShortCode(String shortCode);
}