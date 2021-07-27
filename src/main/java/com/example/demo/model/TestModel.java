package com.example.demo.model;

import com.example.demo.annotations.Mask;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Accessors(chain = true)
public class TestModel {

    @Id
    private Long id;

    @Mask
    private String name;
}
