package com.example.demo.dto;

import com.example.demo.model.Client;
import com.example.demo.model.Manager;
import com.example.demo.model.Operator;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Group {

    private Client client;

    private Manager manager;

    private Operator operator;
}
