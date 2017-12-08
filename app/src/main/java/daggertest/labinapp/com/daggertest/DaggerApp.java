package daggertest.labinapp.com.daggertest;

import dagger.android.AndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.DaggerApplication;
import daggertest.labinapp.com.daggertest.di.DaggerAppComponent;
import timber.log.Timber;

/**
 * Created by ADMIN on 05-12-2017.
 */

public class DaggerApp extends DaggerApplication {

    //  private EmailComponent emailComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

 /*       emailComponent = DaggerEmailComponent.builder()
                .emailModule(new EmailModule("anand@gmail.com"))
                .networkModule(new NetworkModule())
                .build();*/
    }

/*    public EmailComponent getEmailComponent() {
        return emailComponent;
    }*/

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
