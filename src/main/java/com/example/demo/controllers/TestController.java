package com.example.demo.controllers;

import com.example.demo.model.TestModel;
import com.example.demo.model.TestModel1;
import com.example.demo.model.TestModel2;
import com.example.demo.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping
    public TestModel test(@RequestBody TestModel testModel) {
        return testService.test(testModel, new TestModel1().setFullName("Вася").setSex("m"), new TestModel2().setSurname("Петр Иванович"));
    }
}
