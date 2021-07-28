## Spring Boot AOP 기술로 관점 지향 프로그래밍 이해하기
**목차**

1. 부가 기능을 위임하는 전통적인 방법(프록시, 데코레이터)
2. 스프링에서 부가 기능을 위임하는 방법 -  AOP
3. 실습

### 1. 부가 기능을 위임하는 전통적인 방법(프록시, 데코레이터)

**Proxy ?**

- 다른 무언가와 이어지는 인터페이스의 역할을 수행하는 클래스
- 어떤 객체에 대한 접근을 제어하는 용도로 대리인이나 대변인에 해당하는 객체를 제공하는 패턴

**Decorator ?**

- 주어진 상황 및 용도에 따라 어떤 객체에 책임을 덧붙이는 패턴
- 기능 확장이 필요할 때 서브클래싱 대신 쓸 수 있는 유연한 대안 될 수 있음

<img src="/aop/img/img-1.png" width="500px;">

### 2. 스프링에서 부가 기능을 위임하는 방법 -  AOP

**AOP - Aspect Oriented Programming ?**

- 횡단 관심사(cross-cutting concern) 의 분리를 허용함으로써 모듈성을 증가시키 것이 목적인 프로그래밍 패러다임
- 기능의 코드 핵심부를 어수선하게 채우지 않고도 비즈니스 로직에 핵심적이지 않은 동작들을 프로그램에 추가할 수 있게 함

<img src="/aop/img/img-2.png" width="500px;">

**특징**

- 프로그램 구조에 대한 또 다른 사고 방식을 제공 → OOP 를 보완
- OOP 에서의 모듈화 핵심 단위는 Class 이고, AOP 에서의 핵심 모듈화 단위는 Aspect 이다
- Aspect 는 여러 유형과 객체에 걸쳐 있는 트랜잭션 관리와 같은 관심사의 모듈을 가능하게 함

**용어**

- aspect : 여러 클래스에 걸친 관심사의 모듈화
- join point : 메소드 실행 또는 예외 처리와 같은 프로그래밍 실행 중 지점
- advice : 특정한 join point 에서 Aspect 에 의해 행해진 액션 ( "around", "before", "after" 등의 Advice 타입 존재)
- pointcut : join point 중에서 Advice 가 weaving 되어야 할 join point 의 집합을 정의한 것
- introduction : 기존 클래스에 추가적인 메소드나 필드를 선언하여 사용하는 방식
- target object : 하나 이상의 aspect 에서 advice 된 객체 (Spring Aop 에서는 런타임 프록시를 사용하여 구현되므로 이 객체는 항상 프록시 객체가 된다)
- aop proxy : aspect 를 구현하기 위해 aop 프레임워크에 의해 생성된 객체
- weaving : 다른 애플리케이션 유형 또는 객체와 aspect 를 연결하여 권장 개체를 만드는 것
