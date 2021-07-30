package me.aop.v3;

// Real Object (핵심기능만 포함)
public class GreetingMachine implements IGreetingMachine {
    @Override
    public void greet(User user) {
        // 핵심
        System.out.println("어서 오세요!");
    }
}
