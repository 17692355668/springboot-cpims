package com.javakc.springbootcpims.common.base.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @version V1.0.0
 * @author zhouhonggang
 * @className ResultMessage
 * @packageName com.zhou.javakc.component.base.entity.response
 * @description 统一接口返回值-参数封装
 * @date 2020-07-06 17:56
 */
@Getter
@Setter
public class ResultMessage {
    private Integer code;
    private String message;
    private Object data;

    public ResultMessage() {}

    public ResultMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 成功|无参
     * @return ResultMessage
     */
    public static ResultMessage success() {
        ResultMessage result = new ResultMessage();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    /**
     * 成功|带参
     * @param data
     * @return ResultMessage
     */
    public static ResultMessage success(Object data) {
        ResultMessage result = new ResultMessage();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 自定义成功|带参
     * @param resultCode
     * @param data
     * @return ResultMessage
     */
    public static ResultMessage success(ResultCode resultCode, Object data) {
        ResultMessage result = new ResultMessage();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    /**
     * 错误信息|无参
     * @param resultCode
     * @return ResultMessage
     */
    public static ResultMessage failure(ResultCode resultCode) {
        ResultMessage result = new ResultMessage();
        result.setResultCode(resultCode);
        return result;
    }


    /**
     *  错误信息|带参
     * @param resultCode
     * @param data
     * @return ResultMessage
     */
    public static ResultMessage failure(ResultCode resultCode, Object data) {
        ResultMessage result = new ResultMessage();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public void setResultCode(ResultCode code) {
        this.code = code.code();
        this.message = code.message();
    }

    /**
     * 封装响应状态信息
     * @param response 响应
     * @param message 信息
     */
    public static void response(HttpServletResponse response, ResultMessage message)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        String json = null;
        try {
            json = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(json);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

}
