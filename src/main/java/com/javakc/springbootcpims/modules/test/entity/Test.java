package com.javakc.springbootcpims.modules.test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @program: springboot-cpims
 * @description: 测试模块实体类
 * @author: zhou hong gang
 * @create: 2020-10-21 12:30
 */
@Data
@ApiModel(value = "测试实体")
public class Test {
    @ApiModelProperty(name = "id", value = "主键", dataType = "integer")
    private int id;

    @ApiModelProperty(name = "name", value = "姓名", dataType = "string")
    private String name;

    @ApiModelProperty(name = "age", value = "年龄", dataType = "integer")
    private Integer age;

    @ApiModelProperty(name = "birthday", value = "日期", dataType = "date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @ApiModelProperty(name = "address", value = "住址", dataType = "string")
    private String address;
}
