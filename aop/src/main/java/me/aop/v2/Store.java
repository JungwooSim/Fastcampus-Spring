package me.aop.v2;

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
        // 데코레이터
//        IGreetingMachine greetingMachineProxy = new GreetingMachineProxy();
//        greetingMachineProxy.greet(user);

//        IGreetingMachine greetingMachine = new AlarmGreetingMachineDecorator(new GreetingMachine());
//        greetingMachine.greet(user);

//        IGreetingMachine greetingMachine = new DancingGreetingMachineDecorator(new AlarmGreetingMachineDecorator(new GreetingMachine()));
//        greetingMachine.greet(user);

        IGreetingMachine greetingMachine = new AlarmGreetingMachineDecorator(new DancingGreetingMachineDecorator(new GreetingMachine()));
        greetingMachine.greet(user);
    }
}
