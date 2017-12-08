package daggertest.labinapp.com.daggertest.EmailApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by ADMIN on 05-12-2017.
 */
@Module
public class NetworkModule {
    @Singleton
    @Provides
    OkHttpClient providesClient() {
        return new OkHttpClient();
    }
}
