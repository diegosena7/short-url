package com.dsena7.short_url.controller;

import com.dsena7.short_url.model.UrlRequestDTO;
import com.dsena7.short_url.service.impl.ShortUrlServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/short-url")
@RequiredArgsConstructor
@Slf4j
public class ShortUrlController {

    private final ShortUrlServiceImpl shortUrlService;

    @PostMapping
    ResponseEntity<String> insertUrl(@RequestBody @Valid UrlRequestDTO url){
        log.info("- ShortUrlController: Insert url{}: in database", url);

        String urlShorted = shortUrlService.generateShortUrl(url);
        String originalUrl = shortUrlService.insertUrl(url);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .header("Short-Url", urlShorted)
                .location(URI.create(originalUrl))
                .build();
    }
}
