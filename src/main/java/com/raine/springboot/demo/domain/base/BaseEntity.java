package com.raine.springboot.demo.domain.base;


import lombok.Data;

import java.io.Serializable;

@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

}
