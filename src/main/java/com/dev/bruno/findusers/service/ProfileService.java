package com.dev.bruno.findusers.service;

import com.dev.bruno.findusers.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface ProfileService {

    Profile save(Profile book);

    void delete(Profile book);

    Profile findOne(String id);

    Iterable<Profile> findAll();

    Page<Profile> findByName(String name, PageRequest pageRequest);

    List<Profile> findByUsername(String username);
}