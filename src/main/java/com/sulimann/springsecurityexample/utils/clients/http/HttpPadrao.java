package com.sulimann.springsecurityexample.utils.clients.http;

import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class HttpPadrao {

    private final RestTemplate restTemplate = new RestTemplate();
    private String address;
    private String resource;
    private Object request;

    public <T> Optional<T> call(Class<T> responseType) {
        String url = createURL();
        return Optional
                .ofNullable((T) this.restTemplate.exchange(url, method(), null, responseType, new Object[0]).getBody());
    }

    public <T> Optional<T> call(ParameterizedTypeReference<T> responseType) {
        String url = createURL();
        return Optional
                .ofNullable((T) this.restTemplate.exchange(url, method(), null, responseType, new Object[0]).getBody());
    }

    protected String createURL() {
        String url = this.address;
        if (url.endsWith("/"))
            url = url.substring(0, url.lastIndexOf("/") - 1);
        if (!this.resource.startsWith("/"))
            this.resource = "/".concat(this.resource);
        if (this.resource.endsWith("/"))
            this.resource = this.resource.substring(0, this.resource.lastIndexOf("/"));
        return url.concat(this.resource);
    }

    protected abstract HttpMethod method();

}
