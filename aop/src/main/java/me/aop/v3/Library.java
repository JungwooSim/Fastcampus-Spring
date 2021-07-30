package me.aop.v3;

import org.springframework.stereotype.Component;

@Component
public class Library extends StoreAbstract {
    private String name;
    private int visitCountByUser = 0;

    @Override
    public boolean isVIP(User user) {
        return visitCountByUser > 10;
    }

    public void setVisitCountByUser(int visitCountByUser) {
        this.visitCountByUser = visitCountByUser;
    }

    @AlarmGreetingMachine
    public void visitedBy(User user) {
        System.out.println("환영합니다! " + getName() + " 입니다!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
