package com.javakc.springbootcpims.common.base.entity;


import lombok.Data;

import java.util.Date;

/**
 * @program:springboot-cpoms
 * @description:封装实体类公共属性
 */

@Data
public class Base {
    private int id;
    private Integer revision;
    private String created_by;
    private Date created_time;
    private String updated_by;
    private Date updated_time;
}
