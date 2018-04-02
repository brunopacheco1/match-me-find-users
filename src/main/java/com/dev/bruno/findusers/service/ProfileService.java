package com.dev.bruno.findusers.service;

import com.dev.bruno.findusers.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProfileService {

    Profile save(Profile book);

    void delete(Profile book);

    Profile findOne(String id);

    Iterable<Profile> findAll();

    Page<Profile> findByLocation(String author, PageRequest pageRequest);

    List<Profile> findByName(String title);
}