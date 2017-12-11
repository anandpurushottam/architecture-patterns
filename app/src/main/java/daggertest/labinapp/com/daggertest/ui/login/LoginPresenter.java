package daggertest.labinapp.com.daggertest.ui.login;


import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import daggertest.labinapp.com.daggertest.data.model.AppDetail;
import daggertest.labinapp.com.daggertest.data.model.User;
import daggertest.labinapp.com.daggertest.data.model.UserDetailsModel;
import daggertest.labinapp.com.daggertest.data.source.remote.FirebaseUserService;
import daggertest.labinapp.com.daggertest.data.source.remote.UserService;
import daggertest.labinapp.com.daggertest.di.scope.ActivityScope;
import daggertest.labinapp.com.daggertest.util.ActivityUtils;
import daggertest.labinapp.com.daggertest.util.UserNotAuthenticated;
import timber.log.Timber;

import static daggertest.labinapp.com.daggertest.util.ActivityUtils.getApplicationCode;
import static daggertest.labinapp.com.daggertest.util.NullChecker.isNotNull;

@ActivityScope
public class LoginPresenter implements LoginContract.Presenter, OnCompleteListener<AuthResult> {


    private FirebaseUserService firebaseUserService;
    private UserService userService;

    private LoginContract.View mView;
    private LoginActivity mContext;


    public LoginPresenter(LoginActivity activity, FirebaseUserService firebaseUserService, UserService userService) {
        this.mContext = activity;
        this.firebaseUserService = firebaseUserService;
        this.userService = userService;
    }

    @Override
    public void takeView(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    Intent loginWithGoogle() {
        return firebaseUserService.getUserWithGoogle();
    }


    @Override
    public void logInWithFirebase(GoogleSignInAccount account) {

        FirebaseAuth.getInstance().signInWithCredential(GoogleAuthProvider.getCredential(account.getIdToken(), null))
                .addOnCompleteListener((Activity) mContext, this);
    }


    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {

            try {
                processLogin(User.newInstance(FirebaseAuth.getInstance().getCurrentUser()));
            } catch (UserNotAuthenticated userNotAuthenticated) {
                userNotAuthenticated.printStackTrace();
            }

        } else {
            showErrorInActivity(task.getException());
        }
    }

    @Override
    public void processLogin(final User user) throws UserNotAuthenticated {


        userService.createNewUser(user, new AppDetail("GENERAL", "Phy 11"));

        userService.getUserRef(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                if (dataSnapshot.exists()) {
                    //Old User

                    UserDetailsModel userDetailsModel = dataSnapshot.getValue(UserDetailsModel.class);
                    if (userDetailsModel.getListOfApps().get(getApplicationCode(mContext)) == null) {

                        try {
                            userService.updateOldUser(user, new AppDetail("publication", ActivityUtils.getApplicationName(mContext)));
                        } catch (UserNotAuthenticated userNotAuthenticated) {
                            userNotAuthenticated.printStackTrace();
                        }
                    }

                } else {

                    //New User

                    try {
                        userService.createNewUser(user, new AppDetail("publication", ActivityUtils.getApplicationName(mContext)));
                    } catch (UserNotAuthenticated userNotAuthenticated) {
                        userNotAuthenticated.printStackTrace();
                    }

                }

                try {
                    userService.createUpdateFcm(user, "fcm");
                } catch (UserNotAuthenticated userNotAuthenticated) {
                    userNotAuthenticated.printStackTrace();
                }
                //TODO
                // check logic
                // add publication logic
                // add fcm logic by session maneger
                // try to remove try catch block
                // clean code

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        userService.getAppDetailsRef(user).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    AppDetail appDetail = dataSnapshot.getValue(AppDetail.class);
                    if (!isNotNull(appDetail)) {
                        Timber.d(String.valueOf(appDetail.getAppName()));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        goToNextActivity();
    }


    @Override
    public void goToNextActivity() {
        mView.startNextActivity();
    }

    @Override
    public void showErrorInActivity(Exception e) {
        Timber.e(e);
        String error = (e.getLocalizedMessage()) + "";
        mView.showFirebaseAuthenticationFailedMessage(error);
    }


}
