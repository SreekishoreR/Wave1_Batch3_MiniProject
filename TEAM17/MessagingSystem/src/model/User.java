package model;

public class User {
    private final String userId;
    private String name;
    private String department;

    public User(String userId, String name, String department) {
        this.userId = userId;
        this.name = name;
        this.department = department;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
