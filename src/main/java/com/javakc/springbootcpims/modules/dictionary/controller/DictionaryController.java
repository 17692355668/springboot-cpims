package com.javakc.springbootcpims.modules.dictionary.controller;

import com.javakc.springbootcpims.modules.dictionary.entity.Dictionary;
import com.javakc.springbootcpims.modules.dictionary.service.DictionaryService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;
import java.util.List;

/**
 * @program:springboot-cpims
 * @description:数据字典接口
 * @create:2020-10-23
 */
@RestController
@RequestMapping("dictionary")

public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;
    //根据子系统名称获取该子系统下所有的数据字典

    //根据一组分组名称查询所有符合条件的数据字典

    //根据真实值查询表现值  子系统名称，分组名称，真实值

    @GetMapping
    @ApiIgnore
    @PreAuthorize("hasAnyAuthority('dictionary:query')")
    public List<Dictionary> query()
    {
        return dictionaryService.queryAll();
    }

    @GetMapping("{id}")
    @ApiOperation(value="【主键查询】",notes = "主键ID必填信息")
    @PreAuthorize("hasAnyAuthority('dictionary:load')")
    public Dictionary load(@ApiParam(name = "id",value = "主键",required = true,example = "1")@PathVariable int id)
    {
        return dictionaryService.queryById(id);
    }

    @PostMapping
    @ApiOperation(value="【添加】")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "主键",dataType = "integer",required = true),
                    @ApiImplicitParam(name = "systemName",value = "子系统名称",dataType = "string",required = true),
                    @ApiImplicitParam(name = "groupName",value = "分组名称",dataType = "string",required = true),
                    @ApiImplicitParam(name = "realValue",value = "真实值",dataType = "integer",required = true),
                    @ApiImplicitParam(name = "showValue",value = "表现值",dataType = "string",required = true),
                    @ApiImplicitParam(name = "sortValue",value = "排序值",dataType = "integer",required = true),
                    @ApiImplicitParam(name = "remarks",value = "备注信息",dataType = "string",required = true)
            }


    )
    public void insert(@RequestBody Dictionary entity)
    {

       dictionaryService.insert(entity);
    }
    @PostMapping("batch")
    @ApiOperation(value="【删除-批量】")
    @ApiImplicitParam(
            name = "list",
            value = "数据字典数组",
            required = true,
            allowMultiple = true,
            dataType = "Dictionary"
    )
    public void inserts(@RequestBody List<Dictionary> list)
    {

        dictionaryService.inserts(list);
    }

    @PutMapping
    @ApiOperation(value="【修改】")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "主键",dataType = "integer",required = true),
                    @ApiImplicitParam(name = "systemName",value = "子系统名称",dataType = "string",required = true),
                    @ApiImplicitParam(name = "groupName",value = "分组名称",dataType = "string",required = true),
                    @ApiImplicitParam(name = "realValue",value = "真实值",dataType = "integer",required = true),
                    @ApiImplicitParam(name = "showValue",value = "表现值",dataType = "string",required = true),
                    @ApiImplicitParam(name = "sortValue",value = "排序值",dataType = "integer",required = true),
                    @ApiImplicitParam(name = "remarks",value = "备注信息",dataType = "string",required = true)
            }

    )
    public void update(@RequestBody Dictionary entity)
    {
        dictionaryService.update(entity);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value="【删除】",notes="主键ID必填信息")
    public void delete(@ApiParam(name = "id",value = "主键",required = true,example = "1") @PathVariable int id)
    {
        dictionaryService.delete(id);
    }

    @DeleteMapping
    @ApiOperation(value="【删除-批量】",notes="主键ID必填信息")
    @ApiImplicitParam(
            name = "ids",
            value = "主键数组",
            allowMultiple = true,
            dataType = "string",
            required = true
    )
    public void delete(@RequestBody List<Serializable> ids)
    {

        dictionaryService.deletes(ids);
    }

}
