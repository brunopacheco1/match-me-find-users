package com.dev.bruno.findusers.service;

import com.dev.bruno.findusers.model.Profile;
import com.dev.bruno.findusers.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Profile> optionalProfile = repository.findById(id);

        if(optionalProfile.isPresent()) {
            return optionalProfile.get();
        }

        return null;
    }

    public Iterable<Profile> findAll() {
        return repository.findAll();
    }

    public Page<Profile> findByName(String name, PageRequest pageRequest) {
        return repository.findByName(name, pageRequest);
    }

    public List<Profile> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}