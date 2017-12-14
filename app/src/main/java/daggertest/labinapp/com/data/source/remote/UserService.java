package daggertest.labinapp.com.data.source.remote;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import daggertest.labinapp.com.data.model.AppDetail;
import daggertest.labinapp.com.data.model.User;
import daggertest.labinapp.com.data.model.UserDetailsModel;
import daggertest.labinapp.com.util.ActivityUtils;
import daggertest.labinapp.com.util.UserNotAuthenticated;

import static daggertest.labinapp.com.util.ActivityUtils.getApplicationCode;
import static daggertest.labinapp.com.util.NullChecker.isNotNull;


public class UserService {
    private Application application;
    private DatabaseReference databaseRef;

    private AppDetail appDetail;
    public String APP_CODE;
    public String APP_NAME;


    public UserService(Application application) {
        this.application = application;
        this.databaseRef = FirebaseDatabase.getInstance().getReference().child("userDetails");
        APP_NAME = ActivityUtils.getApplicationName(application);
        APP_CODE = ActivityUtils.getApplicationCode(application);
        appDetail = new AppDetail(APP_NAME);
    }


    public DatabaseReference getUserRef(User user) {
        checkUserAuthentication();
        return databaseRef.child(user.getUid());
    }


    private DatabaseReference getAppDetailsRef(User user) {
        return getUserRef(user)
                .child("listOfApps")
                .child(getApplicationCode(application));
    }

    private DatabaseReference getFcmRef(User user) {
        return getAppDetailsRef(user).child("fcmToken");
    }

    public void createNewUser(User user) {

        Map<String, AppDetail> list = new HashMap<String, AppDetail>();
        list.put(getApplicationCode(application), appDetail);

        list.put("demo", new AppDetail(user.getUid()));
        //TODO Fix add demo app to server
        // Added a demo app with "demo" as key in listOfApps
        // Solves error:  Expected a Map while deserializing, but got a class java.util.ArrayList

        UserDetailsModel userDetailsModel = new UserDetailsModel(user.getName(), user.getEmail(), user.getPhone(), list);

        getUserRef(user).setValue(userDetailsModel);

    }

    public void updateOldUser(User user) {
        getAppDetailsRef(user)
                .setValue(appDetail);
    }

    public void createUpdateFcm(User user, String fcm) {
        getFcmRef(user)
                .setValue(fcm);
    }


    private void checkUserAuthentication() {
        if (!isNotNull(FirebaseAuth.getInstance().getCurrentUser()))
            throw new UserNotAuthenticated();

    }


}
