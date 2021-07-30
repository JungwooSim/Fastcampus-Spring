package me.aop.v3;

import org.springframework.stereotype.Component;

@Component
public class Store extends StoreAbstract {
    private String name;
    private int visitCountByUser = 0;

    public String getOperationTime() {
        return "AM 08:00 ~ PM 08:00";
    }

    @AlarmGreetingMachine
    public void visitedBy(User user) {
        // 핵심 1
        this.greeting();
        visitCountByUser++;
    }

    @Override
    public boolean isVIP(User user) {
        return visitCountByUser > 10;
    }

    public void setVisitCountByUser(int visitCountByUser) {
        this.visitCountByUser = visitCountByUser;
    }

    private void greeting() {
        System.out.println("여서오세요");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
