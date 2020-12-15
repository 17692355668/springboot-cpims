package com.javakc.springbootcpims.modules.dictionary.entity;


import com.javakc.springbootcpims.common.base.entity.Base;
import lombok.Data;

/**
 * @program:springboot-cpims
 * @description:数据字典实体类
 * @create:2020-10-23
 */

@Data
public class Dictionary extends Base {
    private String systemName;
    private String groupName;
    private Integer realValue;
    private String showValue;
    private Integer sortValue;
    private String remarks;

}
