package daggertest.labinapp.com.ui.login;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import daggertest.labinapp.com.base.BasePresenter;
import daggertest.labinapp.com.base.BaseView;
import daggertest.labinapp.com.data.model.User;
import daggertest.labinapp.com.util.UserNotAuthenticated;

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
