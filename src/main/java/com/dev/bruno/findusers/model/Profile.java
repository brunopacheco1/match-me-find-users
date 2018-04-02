package com.dev.bruno.findusers.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "match-me", type = "profile")
public class Profile {

    @Id
    private String id;
    private String name;
    private String username;
    private String location;

    public Profile(){}

    public Profile(String id, String name, String username, String location) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getUsername() {
        return username;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}