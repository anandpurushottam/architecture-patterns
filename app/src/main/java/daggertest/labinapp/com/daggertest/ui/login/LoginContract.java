package daggertest.labinapp.com.daggertest.ui.login;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import daggertest.labinapp.com.daggertest.base.BasePresenter;
import daggertest.labinapp.com.daggertest.base.BaseView;
import daggertest.labinapp.com.daggertest.data.model.User;
import daggertest.labinapp.com.daggertest.util.UserNotAuthenticated;

public interface LoginContract {
    interface View extends BaseView<Presenter> {

        void startNextActivity();

        void showFirebaseAuthenticationFailedMessage(String error);

    }

    interface Presenter extends BasePresenter<View> {
        void logInWithFirebase(GoogleSignInAccount account);

        void processLogin(User user) throws UserNotAuthenticated;

        void goToNextActivity();

        void showErrorInActivity(Exception e);
    }

}
