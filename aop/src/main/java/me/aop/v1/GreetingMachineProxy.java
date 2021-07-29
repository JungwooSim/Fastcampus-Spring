package me.aop.v1;

public class GreetingMachineProxy implements IGreetingMachine {
    private GreetingMachine greetingMachine;
    public GreetingMachineProxy() {
        this.greetingMachine = new GreetingMachine();
    }

    @Override
    public void greet(User user) {
        // 부가
        System.out.println(user.getName() + "이(가) 방문했습니다.");

        greetingMachine.greet(user);
    }
}
