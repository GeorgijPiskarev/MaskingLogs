package com.example.log.model;

import com.example.log.util.MaskLog;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Accessors(chain = true)
public class Operator {

    @Id
    private Long id;

    @MaskLog
    private String fullName;

    private String sex;
}
