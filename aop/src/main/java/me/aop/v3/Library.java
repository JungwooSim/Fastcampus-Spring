package me.aop.v3;

import org.springframework.stereotype.Component;

@Component
public class Library {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @AlarmGreetingMachine
    public void visitedBy(User user) {
        System.out.println("환영합니다! " + getName() + " 입니다!");
    }
}
