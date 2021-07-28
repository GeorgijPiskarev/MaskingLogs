package com.example.log.dto;

import com.example.log.model.Client;
import com.example.log.model.Manager;
import com.example.log.model.Operator;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Group {

    private Client client;

    private Manager manager;

    private Operator operator;
}
