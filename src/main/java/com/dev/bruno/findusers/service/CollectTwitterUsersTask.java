package com.dev.bruno.findusers.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class CollectTwitterUsersTask {

    @EventListener(ApplicationReadyEvent.class)
    public void collectUsers() {
        System.out.println("Collecting users");
    }
}