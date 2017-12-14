package daggertest.labinapp.com.di.component;

import javax.inject.Singleton;

import dagger.Component;
import daggertest.labinapp.com.di.module.AppModule;
import daggertest.labinapp.com.di.module.firebase.FirebaseModule;
import daggertest.labinapp.com.di.module.network.NetworkModule;
import daggertest.labinapp.com.di.module.session.SessionModule;
import daggertest.labinapp.com.ui.login.di.LoginActivityComponent;
import daggertest.labinapp.com.ui.login.di.LoginActivityModule;


@Singleton
@Component(
        modules = {
                AppModule.class,
                FirebaseModule.class,
                SessionModule.class,
                NetworkModule.class
        }
)
public interface AppComponent {

    LoginActivityComponent plus(LoginActivityModule activityModule);



}
