package daggertest.labinapp.com.ui.login.di;

import dagger.Module;
import dagger.Provides;
import daggertest.labinapp.com.data.source.local.SessionService;
import daggertest.labinapp.com.data.source.remote.FirebaseUserService;
import daggertest.labinapp.com.data.source.remote.UserService;
import daggertest.labinapp.com.di.scope.ActivityScope;
import daggertest.labinapp.com.ui.login.LoginActivity;
import daggertest.labinapp.com.ui.login.LoginPresenter;

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
    LoginPresenter provideLoginPresenter(FirebaseUserService firebaseUserService,
                                         UserService userService,
                                         SessionService sessionService) { // TODO Fix implementation
        return new LoginPresenter(activity, firebaseUserService, userService,sessionService);
    }


}
