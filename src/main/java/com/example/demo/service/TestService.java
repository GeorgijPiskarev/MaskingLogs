package com.example.demo.service;

import com.example.demo.model.TestModel;
import com.example.demo.model.TestModel1;
import com.example.demo.model.TestModel2;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestService {

    @Autowired
    private Gson gson;

    public TestModel test(TestModel model, TestModel1 testModel1, TestModel2 testModel2) {
        log.error("1 {}", gson.toJson(model));
        log.error("2 {}", testModel1);
        log.error("3 {}", testModel2);
        return model;
    }
}
