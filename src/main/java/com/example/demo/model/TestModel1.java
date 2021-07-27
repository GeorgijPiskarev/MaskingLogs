package com.example.demo.model;

import com.example.demo.annotations.Mask;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Accessors(chain = true)
public class TestModel1 {

    @Id
    private Long id;

    @Mask
    private String fullName;

    private String sex;
}
