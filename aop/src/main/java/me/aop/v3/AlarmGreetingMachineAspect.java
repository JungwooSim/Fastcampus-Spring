package me.aop.v3;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AlarmGreetingMachineAspect {

    // @Before() // 특정 메소드 이전에
    // @After() // 특정 메소드 이후에
    // @AfterReturning // 특정 메소드 리턴 후에
    // @AfterThrowing // 특정 메소드의 throw 를 던지고 난 후에
    @Before(value = "@annotation(AlarmGreetingMachine)")
    public void alarm(JoinPoint joinPoint) {
        /*
        if (joinPoint.getTarget().getClass().getSimpleName().equals("store")) {
            Store target = (Store) joinPoint.getTarget();

            if (target.isVIP()) {
                System.out.println("VIP 유저 입니다! 사은품을 받아가세요!");
            }
        } else {
            Library target = (Library) joinPoint.getTarget();

            if (target.isVIP()) {
                System.out.println("VIP 유저 입니다! 사은품을 받아가세요!");
            }
        }
        */




        Object[] args = joinPoint.getArgs(); // 파라메터들을 배열로 가져올 수 있음 (for 돌려서 필요한 인스턴스 빼서 사용하면됨)
        User user = (User) args[0];

        StoreAbstract storeAbstract = (StoreAbstract) joinPoint.getTarget();
        if (storeAbstract.isVIP(user)) {
            System.out.println("VIP 유저 입니다! 사은품을 받아가세요!");
        }

        System.out.println(user.getName() + "이(가) 방문했습니다.");
    }
}
