package daggertest.labinapp.com.daggertest.di;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ADMIN on 06-12-2017.
 */
@Singleton
@Module
public class FirebaseAuthModule {
    @Provides
    FirebaseAuth provideFireBaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    FirebaseUser provideFireBaseUser() {
        return provideFireBaseAuth().getCurrentUser();
    }
}
