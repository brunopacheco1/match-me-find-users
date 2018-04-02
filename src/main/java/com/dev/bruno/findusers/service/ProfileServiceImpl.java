package com.dev.bruno.findusers.service;

import com.dev.bruno.findusers.model.Profile;
import com.dev.bruno.findusers.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository repository;

    public Profile save(Profile profile) {
        return repository.save(profile);
    }

    public void delete(Profile profile) {
        repository.delete(profile);
    }

    public Profile findOne(String id) {
        return repository.findById(id).get();
    }

    public Iterable<Profile> findAll() {
        return repository.findAll();
    }

    public Page<Profile> findByLocation(String author, PageRequest pageRequest) {
        return repository.findByLocation(author, pageRequest);
    }

    public List<Profile> findByName(String title) {
        return repository.findByName(title);
    }
}