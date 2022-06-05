package io.kimmking.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Klass {

    @Autowired
    List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void dong(){
        System.out.println(students);
    }
    
}
