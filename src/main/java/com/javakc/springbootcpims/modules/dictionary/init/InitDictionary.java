package com.javakc.springbootcpims.modules.dictionary.init;

import com.javakc.springbootcpims.modules.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program:springboot-cpims
 * @description:实现缓存预热
 * @create:2020-10-30
 */
@Component
public class InitDictionary {
    public InitDictionary(@Autowired DictionaryService dictionaryService) {
        dictionaryService.queryAll();


    }
}
