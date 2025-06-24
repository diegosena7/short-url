package com.dsena7.short_url.model;

import org.springframework.data.annotation.Id;
import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("short_urls")
@Builder
public class ShortUrlEntity {

    @Id
    private String id;

    @Field("large_url")
    private String largeUrl;

    @Field("short_url")
    private String shortUrl;
}
