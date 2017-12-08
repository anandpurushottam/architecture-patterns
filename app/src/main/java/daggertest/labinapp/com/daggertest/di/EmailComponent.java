package daggertest.labinapp.com.daggertest.di;

import javax.inject.Singleton;

import dagger.Component;
import daggertest.labinapp.com.daggertest.EmailApi.NetworkModule;
import daggertest.labinapp.com.daggertest.MainActivity;
import daggertest.labinapp.com.daggertest.SecondActivity;
import daggertest.labinapp.com.daggertest.email.Email;
import daggertest.labinapp.com.daggertest.email.EmailModule;
import okhttp3.OkHttpClient;

/**
 * Created by ADMIN on 05-12-2017.
 */
@Singleton
@Component(modules = {EmailModule.class, NetworkModule.class})

public interface EmailComponent {

    Email email();

    OkHttpClient client();

    void inject(MainActivity mainActivity);
    void inject(SecondActivity secondActivity);
}
