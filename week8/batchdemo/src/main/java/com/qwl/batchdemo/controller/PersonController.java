package com.qwl.batchdemo.controller;

import com.qwl.batchdemo.dao.PersonDAO;
import com.qwl.batchdemo.model.Person;
import com.qwl.batchdemo.util.RandomName;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author 齐威龙
 * @date 2020/6/8 15:21
 */
@RestController
public class PersonController {
    @Resource
    PersonDAO personDAO;

    @PostMapping("/addPerson")
    public void addPerson(){
        long startTime = System.currentTimeMillis();
        for(int i=0;i<2000;i++){
            ArrayList<Person> personList  = new ArrayList<>();
            for(int j=0;j<500;j++){
                Person person = new Person();
                int age = (int)(Math.random()*120);
                person.setAge(age);
                person.setName(RandomName.build());
                personList.add(person);
            }
            personDAO.batchAddPerson(personList);
        }
        long endTime = System.currentTimeMillis();
        long time = endTime-startTime;
        System.out.println("耗时："+time+"毫秒");
    }

}
