package daggertest.labinapp.com.daggertest.di.component;

import javax.inject.Singleton;

import dagger.Component;
import daggertest.labinapp.com.daggertest.di.module.AppModule;
import daggertest.labinapp.com.daggertest.di.module.firebase.FirebaseModule;
import daggertest.labinapp.com.daggertest.ui.login.di.LoginActivityComponent;
import daggertest.labinapp.com.daggertest.ui.login.di.LoginActivityModule;


@Singleton
@Component(
        modules = {
                AppModule.class,
                FirebaseModule.class
        }
)
public interface AppComponent {

    LoginActivityComponent plus(LoginActivityModule activityModule);


}
