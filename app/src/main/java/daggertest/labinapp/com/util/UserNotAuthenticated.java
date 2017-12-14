package daggertest.labinapp.com.util;

public class UserNotAuthenticated extends RuntimeException {
    public UserNotAuthenticated() {
        super("User not authenticated");
    }

}