package com.dsena7.short_url.service.impl;

import com.dsena7.short_url.model.UrlRequestDTO;
import com.dsena7.short_url.service.interfaces.RedisService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;
import java.util.Optional;

@Slf4j
@Validated
@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final Duration DEFAULT_CACHE_DURATION = Duration.ofHours(24);

    @Override
    public Optional<String> getShortUrlCache(@Valid UrlRequestDTO keyRedis) {
        try {
            String key = keyRedis.getUrl();
            return Optional.ofNullable(redisTemplate.opsForValue().get(key))
                    .map(Object::toString)
                    .map(value -> {
                        log.info("URL encurtada encontrada no Redis - chave: {}, valor: {}", key, value);
                        return value;
                    });

        } catch (Exception e) {
            log.error("Erro ao buscar valor no Redis para a chave: {}. Erro: {}",
                    keyRedis.getUrl(), e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void saveShortUrlCache(@Valid UrlRequestDTO key, String value) {
        saveShortUrlCache(key, value, DEFAULT_CACHE_DURATION);
    }

    @Override
    public void saveShortUrlCache(@Valid UrlRequestDTO key, String value, Duration duration) {
        try {
            String redisKey = key.getUrl();
            redisTemplate.opsForValue().set(redisKey, value, duration);
            log.info("URL salva no Redis - chave: {}, valor: {}, duração: {}",
                    redisKey, value, duration);
        } catch (Exception e) {
            log.error("Erro ao salvar no Redis - chave: {}, valor: {}. Erro: {}",
                    key.getUrl(), value, e.getMessage());
        }
    }

    @Override
    public boolean deleteShortUrlCache(@NotBlank String key) {
        try {
            boolean deleted = redisTemplate.delete(key);
            if (deleted) {
                log.info("Chave {} removida do Redis com sucesso", key);
                return true;
            }
            log.warn("Chave {} não encontrada no Redis", key);
            return false;
        } catch (Exception e) {
            log.error("Erro ao deletar chave {} do Redis. Erro: {}", key, e.getMessage());
            return false;
        }
    }

    @Override
    public boolean hasKey(@NotBlank String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("Erro ao verificar existência da chave {} no Redis. Erro: {}", key, e.getMessage());
            return false;
        }
    }
}