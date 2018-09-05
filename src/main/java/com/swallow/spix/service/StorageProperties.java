package com.swallow.spix.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "storage")
public class StorageProperties {

    private String location = "images";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
