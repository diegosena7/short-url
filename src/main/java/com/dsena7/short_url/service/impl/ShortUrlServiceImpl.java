package com.dsena7.short_url.service.impl;

import com.dsena7.short_url.model.ShortUrlEntity;
import com.dsena7.short_url.model.UrlRequestDTO;
import com.dsena7.short_url.repository.ShortUrlRepository;
import com.dsena7.short_url.service.interfaces.RedisService;
import com.dsena7.short_url.service.interfaces.ShortUrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShortUrlServiceImpl implements ShortUrlService {

    private final ShortUrlRepository repository;
    private final RedisService redisServiceImpl;

    @Override
    public String insertUrl(UrlRequestDTO originalUrl) {
        // Gera a URL curta
        String shortUrl = generateShortUrl(originalUrl);

        UrlRequestDTO shortUrlDTO = new UrlRequestDTO();
        shortUrlDTO.setUrl(shortUrl);

        // Verifica se já existe no cache usando a URL curta como chave
        return redisServiceImpl.getShortUrlCache(shortUrlDTO)
                .orElseGet(() -> {
                    // Salva no MongoDB
                    ShortUrlEntity entity = ShortUrlEntity.builder()
                            .largeUrl(originalUrl.getUrl())
                            .shortUrl(shortUrl)
                            .build();
                    repository.save(entity);

                    // Salva no Redis usando a URL curta como chave e a URL original como valor
                    redisServiceImpl.saveShortUrlCache(shortUrlDTO, originalUrl.getUrl());

                    log.info("Nova URL processada - Original: {}, Encurtada: {}",
                            originalUrl.getUrl(), shortUrl);
                    return originalUrl.getUrl();
                });
    }


    public String generateShortUrl(UrlRequestDTO url) {
        String originalUrl = url.getUrl();
        String prefix = originalUrl.length() > 10 ? originalUrl.substring(0, 10) : originalUrl;
        return prefix + "dsena7";
    }
}
