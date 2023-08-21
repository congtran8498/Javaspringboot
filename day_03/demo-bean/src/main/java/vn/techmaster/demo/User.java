package vn.techmaster.demo;

import org.springframework.stereotype.Component;

@Component
public class User {
    public User(){
        System.out.println("user created");
    }
    public void work(){
        System.out.println("user work");
    }
}
