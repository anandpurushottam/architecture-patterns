package daggertest.labinapp.com.daggertest.data.source.remote;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import daggertest.labinapp.com.daggertest.data.model.AppDetail;
import daggertest.labinapp.com.daggertest.data.model.User;
import daggertest.labinapp.com.daggertest.data.model.UserDetailsModel;
import daggertest.labinapp.com.daggertest.util.UserNotAuthenticated;

import static daggertest.labinapp.com.daggertest.util.ActivityUtils.getApplicationCode;
import static daggertest.labinapp.com.daggertest.util.NullChecker.isNotNull;


public class UserService {
    private Application application;
    private DatabaseReference databaseRef;


    public UserService(Application application) {
        this.application = application;
        this.databaseRef = FirebaseDatabase.getInstance().getReference().child("userDetails");
    }


    public DatabaseReference getUserRef(String userUid) throws UserNotAuthenticated {
        checkUserAuthority();
        return databaseRef.child(userUid);
    }


    public DatabaseReference getAppDetailsRef(User user) throws UserNotAuthenticated {
        return getUserRef(user.getUid())
                .child("listOfApps")
                .child(getApplicationCode(application));
    }

    public DatabaseReference getFcmRef(User user) throws UserNotAuthenticated {
        return getAppDetailsRef(user).child("fcmToken");
    }

    public void createNewUser(User user, AppDetail appDetail) throws UserNotAuthenticated {

        Map<String, AppDetail> list = new HashMap<String, AppDetail>();
        list.put(getApplicationCode(application), appDetail);
        list.put("demo", new AppDetail("", user.getUid()));

        UserDetailsModel userDetailsModel = new UserDetailsModel(user.getName(), user.getEmail(), user.getPhone(), list);

        getUserRef(user.getUid()).setValue(userDetailsModel);

    }

    public void updateOldUser(User user, AppDetail appDetail) throws UserNotAuthenticated {
        getAppDetailsRef(user)
                .setValue(appDetail);
    }

    public void createUpdateFcm(User user, String fcm) throws UserNotAuthenticated {
        getFcmRef(user)
                .setValue(fcm);
    }

    private void checkUserAuthority() throws UserNotAuthenticated {
        if (!isNotNull(FirebaseAuth.getInstance().getCurrentUser()))
            throw new UserNotAuthenticated();
    }

}
