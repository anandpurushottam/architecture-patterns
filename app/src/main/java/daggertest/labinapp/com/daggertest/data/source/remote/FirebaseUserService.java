package daggertest.labinapp.com.daggertest.data.source.remote;

import android.app.Application;
import android.content.Intent;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import daggertest.labinapp.com.daggertest.R;
import daggertest.labinapp.com.daggertest.base.BaseActivity;
import daggertest.labinapp.com.daggertest.data.model.User;


public class FirebaseUserService {
    private Application application;

    private FirebaseAuth firebaseAuth;

    // for google
    private GoogleApiClient googleApiClient;


    public FirebaseUserService(Application application) {
        this.application = application;
        this.firebaseAuth = FirebaseAuth.getInstance();
        initializeGoogleApiClient();
    }

    private void initializeGoogleApiClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(application.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(application)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        googleApiClient.connect();
    }


    public Intent getUserWithGoogle() {
        if (googleApiClient.isConnected()) {
            googleApiClient.clearDefaultAccountAndReconnect();
        }

        return Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
    }

    public Task<AuthResult> getAuthWithGoogle(final BaseActivity activity, GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        return firebaseAuth.signInWithCredential(credential);
    }


    public void logOut() {
        firebaseAuth.signOut();
        Auth.GoogleSignInApi.signOut(googleApiClient);

    }

    public void deleteUser(String uid) {

    }

    public void updateUser(User user) {

    }
}
