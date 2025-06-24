package com.dsena7.short_url.service.interfaces;


import com.dsena7.short_url.model.UrlRequestDTO;

public interface ShortUrlService {
    
    String insertUrl(UrlRequestDTO url);
}
