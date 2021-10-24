package io.kimmking.spring02;

import io.kimmking.aop.ISchool;
import io.kimmking.spring01.Student;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@Data
public class SchoolWithoutSpring implements ISchool {
    
    @Override
    public void ding(){

        System.out.println("original ding called.");
        
    }
    
}
