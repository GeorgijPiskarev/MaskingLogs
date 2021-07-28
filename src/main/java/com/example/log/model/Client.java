package com.example.log.model;

import com.example.log.util.MaskLog;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Id;

@Data
@Accessors(chain = true)
public class Client {

    @Id
    private Long id;

    @MaskLog
    private String name;
}
