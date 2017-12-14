package daggertest.labinapp.com.ui.login.di;

import dagger.Subcomponent;
import daggertest.labinapp.com.di.scope.ActivityScope;
import daggertest.labinapp.com.ui.login.LoginActivity;


@ActivityScope
@Subcomponent(
        modules = {
                LoginActivityModule.class
        }
)
public interface LoginActivityComponent {
    LoginActivity inject(LoginActivity activity);
}
