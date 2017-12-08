package daggertest.labinapp.com.daggertest.email;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import daggertest.labinapp.com.daggertest.EmailApi.EmailApi;
import okhttp3.OkHttpClient;

/**
 * Created by ADMIN on 05-12-2017.
 */
@Module
public class EmailModule {
    private String emailUrl;

    public EmailModule(String emailUrl) {
        this.emailUrl = emailUrl;
    }

    @Singleton
    @Provides
    Email provideEmail(EmailApi client) {
        return new Email(emailUrl, client);
    }

    @Singleton
    @Provides
    EmailApi provideEmailApi(OkHttpClient client) {
        return new EmailApi(client);
    }

}
