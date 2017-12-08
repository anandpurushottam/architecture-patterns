package daggertest.labinapp.com.daggertest.login.di;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import daggertest.labinapp.com.daggertest.di.scope.ActivityScope;
import daggertest.labinapp.com.daggertest.login.LoginActivity;

/**
 * Created by ADMIN on 07-12-2017.
 */
@ActivityScope
@Component(modules = {LoginModule.class, AndroidInjectionModule.class, AndroidSupportInjectionModule.class})
public interface LoginComponent extends AndroidInjector<LoginActivity> {

    void inject(LoginActivity loginActivity);


}
