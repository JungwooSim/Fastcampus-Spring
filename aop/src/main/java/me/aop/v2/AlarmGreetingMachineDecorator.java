package me.aop.v2;

public class AlarmGreetingMachineDecorator extends GreetingMachineDecorator {
    public AlarmGreetingMachineDecorator(IGreetingMachine greetingMachine) {
        super(greetingMachine);
    }

    @Override
    public void greet(User user) {
        // 부가 기능 - 알람
        alarm(user);

        // 핵심
        greetingMachine.greet(user);
    }

    private void alarm(User user) {
        System.out.println(user.getName() + "이(가) 방문했습니다.");
    }
}
