package com.javakc.springbootcpims.modules.test.controller;

import com.javakc.springbootcpims.modules.test.entity.Test;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @program: springboot-cpims
 * @description: 测试模块表现层实现类
 * @author: zhou hong gang
 * @create: 2020-10-21 12:29
 */
@RestController
@RequestMapping("test")
@Api(tags = "【测试管理】【测试接口】")
public class TestController {

    @PostMapping
    @ApiOperation(value = "添加")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "name", value = "姓名", dataType = "string", required = true),
        @ApiImplicitParam(name = "age", value = "年龄", dataType = "integer", required = true),
        @ApiImplicitParam(name = "birthday", value = "出生日期", dataType = "date", required = true),
        @ApiImplicitParam(name = "address", value = "家庭住址", dataType = "string")
    })
    public void insert(@RequestBody @ApiIgnore Test entity)
    {
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "【删除】", notes = "主键ID必填信息")
    public void delete(@ApiParam(name = "id", value = "主键", required = true, example = "1") @PathVariable int id)
    {
    }

    @PutMapping
    @ApiOperation(value = "【修改】", notes = "主键ID必填信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键", dataType = "integer", required = true),
        @ApiImplicitParam(name = "name", value = "姓名", dataType = "string", required = true),
        @ApiImplicitParam(name = "age", value = "年龄", dataType = "integer", required = true),
        @ApiImplicitParam(name = "birthday", value = "出生日期", dataType = "date", required = true),
        @ApiImplicitParam(name = "address", value = "家庭住址", dataType = "string")
    })
    public void update()
    {
    }

    @GetMapping(value = "{id}")
    @ApiOperation(value = "【主键查询】", notes = "主键ID必填信息")
    public void get(@ApiParam(name = "id", value = "主键", required = true, example = "1") @PathVariable int id)
    {
    }

    @GetMapping
    @ApiOperation(value = "【查询全部】", notes = "无需传入任何参数")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "恭喜您， 执行成功！"),
        @ApiResponse(responseCode = "400", description = "请求参数填写异常！"),
        @ApiResponse(responseCode = "401", description = "当前请求未经认证！"),
        @ApiResponse(responseCode = "403", description = "当前请求未经授权！"),
        @ApiResponse(responseCode = "404", description = "当前请求地址未找到！"),
        @ApiResponse(responseCode = "500", description = "服务器执行过程出现异常！")
    })
    public void query()
    {
    }

}
