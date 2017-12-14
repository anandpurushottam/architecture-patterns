package daggertest.labinapp.com.base;

import android.app.Application;
import android.content.Context;

import daggertest.labinapp.com.daggertest.BuildConfig;
import daggertest.labinapp.com.di.component.AppComponent;
import daggertest.labinapp.com.di.component.DaggerAppComponent;
import daggertest.labinapp.com.di.module.AppModule;
import daggertest.labinapp.com.di.module.firebase.FirebaseModule;
import daggertest.labinapp.com.di.module.network.NetworkModule;
import daggertest.labinapp.com.di.module.session.SessionModule;
import timber.log.Timber;


public class BaseApplication extends Application {
    private AppComponent appComponent;


    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        initAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .firebaseModule(new FirebaseModule())
                .sessionModule(new SessionModule())
                .networkModule(new NetworkModule("https://api.github.com/")) //TODO FIX network module
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


}
