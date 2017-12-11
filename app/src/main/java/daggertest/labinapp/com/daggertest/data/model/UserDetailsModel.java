package daggertest.labinapp.com.daggertest.data.model;


import java.io.Serializable;
import java.util.Map;


public class UserDetailsModel implements Serializable {
    private String name;
    private String email;
    private String phone;
    private Map<String, AppDetail> listOfApps;


    public UserDetailsModel() {
    }

    public UserDetailsModel(String name, String email, String phone, Map<String, AppDetail> listOfApps) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.listOfApps = listOfApps;
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

    public Map<String, AppDetail> getListOfApps() {
        return listOfApps;
    }

    public void setListOfApps(Map<String, AppDetail> listOfApps) {
        this.listOfApps = listOfApps;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
