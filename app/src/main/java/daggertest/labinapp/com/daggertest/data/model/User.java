package daggertest.labinapp.com.daggertest.data.model;

import android.support.annotation.Keep;

import java.io.Serializable;

/**
 * Created by ADMIN on 06-12-2017.
 */
@Keep
public class User implements Serializable {
    private String name;
    private String email;
    private String profilePic;

    public User() {
    }

    public User(String name, String email, String profilePic) {
        this.name = name;
        this.email = email;
        this.profilePic = profilePic;
    }

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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

}
