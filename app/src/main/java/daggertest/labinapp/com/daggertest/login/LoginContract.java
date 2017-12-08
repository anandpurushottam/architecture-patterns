package daggertest.labinapp.com.daggertest.login;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import daggertest.labinapp.com.daggertest.base.BasePresenter;
import daggertest.labinapp.com.daggertest.base.BaseView;

/**
 * Created by ADMIN on 06-12-2017.
 */

public interface LoginContract {
    interface View extends BaseView<Presenter> {

        void startNextActivity();

        void showFirebaseAuthenticationFailedMessage(String error);

    }

    interface Presenter extends BasePresenter<View> {
        void logInWithFirebase(GoogleSignInAccount account);
    }

}
