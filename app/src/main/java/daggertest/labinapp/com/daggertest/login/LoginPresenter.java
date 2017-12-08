package daggertest.labinapp.com.daggertest.login;


import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import daggertest.labinapp.com.daggertest.di.ActivityScoped;
import timber.log.Timber;

/**
 * Created by ADMIN on 06-12-2017.
 */

@ActivityScoped
public class LoginPresenter implements LoginContract.Presenter {


    private LoginContract.View mView;
    private LoginActivity mContext;

    public LoginPresenter(LoginActivity loginActivity) {
        this.mContext = loginActivity;
    }

    @Override
    public void takeView(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public void logInWithFirebase(GoogleSignInAccount account) {
        Timber.d("Token %s", account.getIdToken());
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Timber.d("logInWithFirebase:onComplete:%s", task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Timber.e(task.getException());
                            String error = (task.getException().getLocalizedMessage()) + "";
                            mView.showFirebaseAuthenticationFailedMessage(error);

                        } else {
                            mView.startNextActivity();
                        }
                    }
                });

    }
}
