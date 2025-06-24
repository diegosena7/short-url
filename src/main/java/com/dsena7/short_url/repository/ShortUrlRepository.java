package com.dsena7.short_url.repository;

import com.dsena7.short_url.model.ShortUrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlRepository extends MongoRepository<ShortUrlEntity, String> {
}
