package daggertest.labinapp.com.di.module.firebase;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import daggertest.labinapp.com.data.source.remote.UserService;
import daggertest.labinapp.com.data.source.remote.FirebaseUserService;


@Module
public class FirebaseModule {
    @Provides
    @Singleton
    public FirebaseUserService provideFirebaseUserService(Application application) {
        return new FirebaseUserService(application);
    }

    @Provides
    @Singleton
    public UserService provideUserService(Application application) {
        return new UserService(application);
    }
}
