package com.dev.bruno.findusers.rest;

import com.dev.bruno.findusers.model.Profile;
import com.dev.bruno.findusers.service.ProfileService;
import com.dev.bruno.findusers.service.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProfileRest {

    @Autowired
    private ProfileService service;

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public Profile get(@PathVariable(value="id") String id) {
        return service.findOne(id);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public Iterable<Profile> list() {
        return service.findAll();
    }
}