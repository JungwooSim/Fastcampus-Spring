package me.aop.v3;

public class DancingGreetingMachineDecorator extends GreetingMachineDecorator {

    public DancingGreetingMachineDecorator(IGreetingMachine greetingMachine) {
        super(greetingMachine);
    }

    @Override
    public void greet(User user) {
        // 부가
        dancing();

        // 핵심
        greetingMachine.greet(user);
    }

    private void dancing() {
        System.out.println("춤 추는 중 ~");
    }
}
