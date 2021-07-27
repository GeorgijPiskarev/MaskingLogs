package com.example.demo.controllers;

import com.example.demo.dto.Group;
import com.example.demo.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/test")
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping
    public Group test(@RequestBody Group group) {
        return logService.test(group);
    }
}
