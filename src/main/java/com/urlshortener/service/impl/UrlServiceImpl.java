package com.urlshortener.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urlshortener.model.UrlMapping;
import com.urlshortener.repository.UrlRepository;
import com.urlshortener.servic.UrlService;
import com.urlshortener.util.Base62Encoder;
//
//@Service
//public class UrlServiceImpl implements UrlService {
//
//    @Autowired
//    private UrlRepository repository;
//
//    // generate short URL
//    @Override
//    public String shortenUrl(String originalUrl) {
//
//        UrlMapping mapping = new UrlMapping();
//        mapping.setOriginalUrl(originalUrl);
//
//        repository.save(mapping);
//
//        // convert DB ID → Base62
//        String shortCode = Base62Encoder.encode(mapping.getId());
//
//        mapping.setShortCode(shortCode);
//
//        repository.save(mapping);
//
//        return "http://localhost:8080/" + shortCode;
//    }
//
//    // retrieve original URL
//    @Override
//    public String getOriginalUrl(String shortCode) {
//
//        UrlMapping mapping = repository.findByShortCode(shortCode);
//
//        if(mapping != null) {
//
//            mapping.setClickCount(mapping.getClickCount()+1);
//
//            repository.save(mapping);
//
//            return mapping.getOriginalUrl();
//        }
//
//        return null;
//    }
//}

import java.security.SecureRandom;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlRepository repository;

    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 8;
    private static final SecureRandom random = new SecureRandom();

    // generate random short code
    private String generateShortCode() {
        StringBuilder sb = new StringBuilder(SHORT_URL_LENGTH);

        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int index = random.nextInt(BASE62.length());
            sb.append(BASE62.charAt(index));
        }

        return sb.toString();
    }

    // ensure uniqueness
    private String generateUniqueShortCode() {
        String code;
        do {
            code = generateShortCode();
        } while (repository.findByShortCode(code) != null);

        return code;
    }

    // generate short URL
    @Override
    public String shortenUrl(String originalUrl) {

        String shortCode = generateUniqueShortCode(); // 🔥 CHANGE HERE

        UrlMapping mapping = new UrlMapping();
        mapping.setOriginalUrl(originalUrl);
        mapping.setShortCode(shortCode);

        repository.save(mapping);

//        return "http://localhost:8080/" + shortCode;
        return "https://short.ly/" + shortCode;
    }

    // retrieve original URL (no change)
    @Override
    public String getOriginalUrl(String shortCode) {

        UrlMapping mapping = repository.findByShortCode(shortCode);

        if(mapping != null) {
            mapping.setClickCount(mapping.getClickCount() + 1);
            repository.save(mapping);
            return mapping.getOriginalUrl();
        }

        return null;
    }
}