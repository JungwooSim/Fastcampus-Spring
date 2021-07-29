package me.aop.v2;

public class User {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String greeting() {
        return "hello";
    }

    public void visitTo(Store store) {
//        // store 에 user 방문을 알림
        store.visitedBy(this);
    }
}
