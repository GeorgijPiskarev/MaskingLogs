package com.example.demo.service;

import com.example.demo.dto.Group;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LogService {

    @Autowired
    private Gson gson;

    public Group test(Group group) {
        log.error("toString: {}", group);
        log.error("json: {}", gson.toJson(group));
        return group;
    }
}
