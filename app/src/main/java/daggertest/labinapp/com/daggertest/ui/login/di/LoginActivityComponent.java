package daggertest.labinapp.com.daggertest.ui.login.di;

import dagger.Subcomponent;
import daggertest.labinapp.com.daggertest.di.scope.ActivityScope;
import daggertest.labinapp.com.daggertest.ui.login.LoginActivity;


@ActivityScope
@Subcomponent(
        modules = {
                LoginActivityModule.class
        }
)
public interface LoginActivityComponent {
    LoginActivity inject(LoginActivity activity);
}
