package daggertest.labinapp.com.daggertest.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import daggertest.labinapp.com.daggertest.DaggerApp;


@Singleton
@Component(modules = {
        ApplicationModule.class,
        AndroidInjectionModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class


})
public interface AppComponent extends AndroidInjector<DaggerApp> {
    //TasksRepository getTasksRepository();
    //  GoogleSignInOptions googleSignInOptions();
    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.

    // Application will just be provided into our app graph now.
    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
