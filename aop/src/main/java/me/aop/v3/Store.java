package me.aop.v3;

import org.springframework.stereotype.Component;

@Component
public class Store {
    private String name;

    public String getOperationTime() {
        return "AM 08:00 ~ PM 08:00";
    }

    @AlarmGreetingMachine
    public void visitedBy(User user) {
        // 핵심 1
        this.greeting();
    }

    private void greeting() {
        System.out.println("여서오세요");
    }
}
