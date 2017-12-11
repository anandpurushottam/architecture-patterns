package daggertest.labinapp.com.daggertest.base;

import android.app.Application;
import android.content.Context;

import daggertest.labinapp.com.daggertest.BuildConfig;
import daggertest.labinapp.com.daggertest.di.component.AppComponent;
import daggertest.labinapp.com.daggertest.di.component.DaggerAppComponent;
import daggertest.labinapp.com.daggertest.di.module.AppModule;
import daggertest.labinapp.com.daggertest.di.module.firebase.FirebaseModule;
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
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


}
