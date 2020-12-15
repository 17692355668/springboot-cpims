package com.javakc.springbootcpims.common.base.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.nio.file.AccessDeniedException;

/**
 * @program:springboot-cpims
 * @description:统一封装接口返回数据
 * @create:2020-10-28
 */
@RestControllerAdvice(basePackages = "com.javakc.springbootcpims.modules")
public class ResultAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 配置拦截规则
     * @param methodParameter
     * @param aClass
     * @return
     */

        @Override
        public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
            return true;
        }

    @Override
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(object instanceof ResultMessage)
            return object;
        return ResultMessage.success(object);
    }
    /**
     * @ResponseBody
     * -----封装---------
     * 把接口返回的对象解析成json
     * 把json通过response传输到客户端
     */


    @ExceptionHandler(value = {Exception.class})
    public ResultMessage serverInternal(Exception ex) {
        if(ex instanceof HttpMessageNotReadableException)

            return ResultMessage.failure(ResultCode.PARAM_TYPE_BIND_ERROR);

        else if(ex instanceof AccessDeniedException)
            return ResultMessage.failure(ResultCode.PERMISSION_NO_ACCESS);
        else
            return ResultMessage.failure(ResultCode.INTERFACE_INNER_INVOKE_ERROR);

    }

}
