package com.example.demo.model;

import com.example.demo.util.Mask;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Id;

@Data
@Accessors(chain = true)
public class Client {

    @Id
    private Long id;

    @Mask
    private String name;
}
