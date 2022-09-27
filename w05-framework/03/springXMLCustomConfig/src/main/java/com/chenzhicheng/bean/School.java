package com.chenzhicheng.bean;

import lombok.Data;


@Data
public class School implements ISchool {
    
    // Resource 
    Klass class1;
    
    Student student100;
    
    @Override
    public void ding(){
    
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student100);
        
    }
    
}
