package daggertest.labinapp.com.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class AppDetail implements Serializable {

    private String userType;
    private boolean isPaidCustomer;
    private String activationKey;
    private String appName;
    private String dateOfPurchase;
    private String source;
    private String fcmToken;
    private final String DEFAULT_USER_TYPE = "GENERAL";

    private AppDetail() {
    }

    public AppDetail(String appName) {
        this.appName = appName;
        userType = DEFAULT_USER_TYPE;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userType", userType);
        result.put("isPaidCustomer", isPaidCustomer);
        result.put("activationKey", activationKey);
        result.put("appName", appName);
        result.put("dateOfPurchase", dateOfPurchase);
        result.put("source", source);
        result.put("fcmToken", fcmToken);
        return result;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public boolean isPaidCustomer() {
        return isPaidCustomer;
    }

    public void setPaidCustomer(boolean paidCustomer) {
        isPaidCustomer = paidCustomer;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
