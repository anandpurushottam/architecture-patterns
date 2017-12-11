package daggertest.labinapp.com.daggertest.ui.login.di;

import android.support.v7.app.AlertDialog;

import dagger.Module;
import dagger.Provides;
import daggertest.labinapp.com.daggertest.data.source.remote.UserService;
import daggertest.labinapp.com.daggertest.data.source.remote.FirebaseUserService;
import daggertest.labinapp.com.daggertest.di.scope.ActivityScope;
import daggertest.labinapp.com.daggertest.ui.login.LoginActivity;
import daggertest.labinapp.com.daggertest.ui.login.LoginPresenter;

@Module
public class LoginActivityModule {

    private LoginActivity activity;

    public LoginActivityModule(LoginActivity activity) {
        this.activity = activity;
    }

    @ActivityScope
    @Provides
    LoginActivity provideLoginActivity() {
        return activity;
    }

    @ActivityScope
    @Provides
    LoginPresenter provideLoginPresenter(FirebaseUserService firebaseUserService, UserService userService) {
        return new LoginPresenter(activity, firebaseUserService, userService);
    }


}
