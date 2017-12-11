package daggertest.labinapp.com.daggertest.util;

public class UserNotAuthenticated extends Exception {
    public UserNotAuthenticated() {
        super("User not authenticated");
    }
}