package com.dev.bruno.findusers.repository;

import com.dev.bruno.findusers.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProfileRepository extends ElasticsearchRepository<Profile, String> {

    Page<Profile> findByName(String name, Pageable pageable);

    List<Profile> findByUsername(String username);
}
