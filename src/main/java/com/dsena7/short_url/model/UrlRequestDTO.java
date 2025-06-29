package com.dsena7.short_url.model;

import jakarta.validation.constraints.NotBlank;

public record UrlRequestDTO(
        @NotBlank(message = "A URL não pode estar vazia")
        String url
) {

    public UrlRequestDTO() {
        this("");
    }


    @Override
    public String toString() {
        return url;
    }

    public String url() {
        return url;
    }
}


