package me.aop.v1;

import lombok.Data;

@Data
public class Store {
    private String name;

    private int visitCountByUser = 0;

    public String getOperationTime() {
        return "AM 08:00 ~ PM 08:00";
    }

    public void visitedBy(User user) {
        // 핵심 1
        this.greeting(user);
    }

    private void greeting(User user) {
        // Proxy pattern
        // 핵심 기능만 사용할 때
//        IGreetingMachine greetingMachineProxy = new GreetingMachine();
//        greetingMachineProxy.greet(user);

        // 부가기능 포함해서 사용할 때
        IGreetingMachine greetingMachineProxy = new GreetingMachineProxy();
        greetingMachineProxy.greet(user);
    }
}
