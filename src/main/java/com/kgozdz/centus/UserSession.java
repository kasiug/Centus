package com.kgozdz.centus;

public final class UserSession {

    private static UserSession instance;

    private int userId;
    private String userName;

    private UserSession(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public static UserSession getInstance(int userId, String userName) {
        if (instance == null) {
            instance = new UserSession(userId, userName);
        }
        return instance;
    }

    public static int getUserId() {
        return instance != null ? instance.userId : 0;
    }

    public static String getUserName() {
        return instance != null ? instance.userName : null;
    }

    public static void cleanUserSession() {
       instance = null;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userId='" + userId + '\'' +
                "userName'" + userName + '\'' +
                '}';
    }
}
