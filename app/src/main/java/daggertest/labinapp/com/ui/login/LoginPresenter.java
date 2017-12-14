package daggertest.labinapp.com.ui.login;


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

import daggertest.labinapp.com.data.model.User;
import daggertest.labinapp.com.data.model.UserDetailsModel;
import daggertest.labinapp.com.data.source.local.SessionService;
import daggertest.labinapp.com.data.source.remote.FirebaseUserService;
import daggertest.labinapp.com.data.source.remote.UserService;
import daggertest.labinapp.com.di.scope.ActivityScope;
import daggertest.labinapp.com.util.UserNotAuthenticated;
import timber.log.Timber;

import static daggertest.labinapp.com.util.NullChecker.isNotNull;
import static daggertest.labinapp.com.util.NullChecker.isNull;

@ActivityScope
public class LoginPresenter implements LoginContract.Presenter, OnCompleteListener<AuthResult> {


    private FirebaseUserService firebaseUserService;
    private UserService userService;
    private SessionService session;

    private LoginContract.View mView;
    private LoginActivity mContext;

    public LoginPresenter(LoginActivity activity, FirebaseUserService firebaseUserService, UserService userService, SessionService session) {
        this.mContext = activity;
        this.firebaseUserService = firebaseUserService;
        this.userService = userService;
        this.session = session;
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
                .addOnCompleteListener(mContext, this);
    }


    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {


            processLogin(User.newInstance(FirebaseAuth.getInstance().getCurrentUser()));


        } else {
            showErrorInActivity(task.getException());
        }
    }

    @Override
    public void processLogin(final User user) throws UserNotAuthenticated {

        userService.getUserRef(user).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (isOldUser(dataSnapshot)) {
                    //Old User
                    if (isAppDetailMissing(dataSnapshot))
                        userService.updateOldUser(user);
                } else {
                    //New User
                    userService.createNewUser(user);
                }
                userService.createUpdateFcm(user, session.getFcmToken());
                session.createLoginSession();
                //TODO
                // Add retrofit for network implementation

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        goToNextActivity();

    }

    private boolean isOldUser(DataSnapshot dataSnapshot) {
        return dataSnapshot.exists();
    }

    private boolean isAppDetailMissing(DataSnapshot dataSnapshot) {
        if (dataSnapshot.exists()) {
            UserDetailsModel userDetailsModel = dataSnapshot.getValue(UserDetailsModel.class);

            if (isNotNull(userDetailsModel) && isNotNull(userDetailsModel.getListOfApps())) {
                return isNull(userDetailsModel.getListOfApps().get(userService.APP_CODE));
            }
        }
        return false;

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
