package io.kimmking.spring;

import org.springframework.beans.factory.annotation.Autowired;

public class School implements ISchool {
    
    // Resource 
    @Autowired(required = true) //primary
    Klass class1;
    
    Student student100;
    
    @Override
    public void ding(){
    
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student100);
        
    }
    
}
