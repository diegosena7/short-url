package com.dsena7.short_url.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlRequestDTO {
    @NotBlank(message = "A URL não pode estar vazia")
    private String url;

    @Override
    public String toString() {
        return url;
    }
}

