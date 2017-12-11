package daggertest.labinapp.com.daggertest.data.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import daggertest.labinapp.com.daggertest.util.UserNotAuthenticated;

@IgnoreExtraProperties
public class User {
    private String uid;
    private String name;
    private String email;
    private String phone;

    public static User newInstance(FirebaseUser firebaseUser, String phone) throws UserNotAuthenticated {
        User user = newInstance(firebaseUser);
        user.setPhone(phone);
        return user;
    }

    public static User newInstance(FirebaseUser firebaseUser) throws UserNotAuthenticated {

        if (firebaseUser == null) {
            throw new UserNotAuthenticated();
        }
        User user = new User();
        user.setUid(firebaseUser.getUid());
        user.setEmail(firebaseUser.getEmail());
        user.setName(firebaseUser.getDisplayName());

        return user;
    }

    private User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Exclude
    public String getUid() {
        return uid;
    }

    public void setUid(@NonNull String uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
