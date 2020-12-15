package com.javakc.springbootcpims.modules.dictionary.service;


import com.javakc.springbootcpims.common.base.service.BaseService;
import com.javakc.springbootcpims.modules.dictionary.dao.DictionaryDao;
import com.javakc.springbootcpims.modules.dictionary.entity.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program:springboot-cpims
 * @description:数据字典逻辑层
 * @create:2020-10-23
 */
@Service
public class DictionaryService extends BaseService<DictionaryDao, Dictionary> {



}
