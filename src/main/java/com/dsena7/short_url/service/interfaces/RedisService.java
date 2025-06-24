package com.dsena7.short_url.service.interfaces;

import com.dsena7.short_url.model.UrlRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.time.Duration;
import java.util.Optional;

public interface RedisService {

    Optional<String> getShortUrlCache(@Valid UrlRequestDTO keyRedis);
    void saveShortUrlCache(@Valid UrlRequestDTO key, String value);
    void saveShortUrlCache(@Valid UrlRequestDTO key, String value, Duration duration);
    boolean deleteShortUrlCache(@NotBlank String key);
    boolean hasKey(@NotBlank String key);

}
