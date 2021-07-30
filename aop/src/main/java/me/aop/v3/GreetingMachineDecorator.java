package me.aop.v3;

public abstract class GreetingMachineDecorator implements IGreetingMachine {
    protected IGreetingMachine greetingMachine;

    public GreetingMachineDecorator(IGreetingMachine greetingMachine) {
        this.greetingMachine = greetingMachine;
    }
}
