package com.qwl.batchdemo.dao;

import com.qwl.batchdemo.model.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 齐威龙
 * @date 2020/6/8 15:07
 */
@Mapper
public interface PersonDAO {
    /**
     * 批量插入
     * @return
     */
    int batchAddPerson(List<Person> personList);
}
