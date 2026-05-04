package com.urlshortener.model;
import jakarta.persistence.*;

@Entity
@Table(name="url_mapping")
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // original long URL
    @Column(length = 2048)
    private String originalUrl;

    // generated short code
    @Column(unique = true)
    private String shortCode;

    // number of clicks
    private int clickCount;

    public Long getId() { return id; }

    public String getOriginalUrl() { return originalUrl; }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortCode() { return shortCode; }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public int getClickCount() { return clickCount; }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }
}
