package daggertest.labinapp.com.daggertest.EmailApi;

import java.util.Timer;

import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import timber.log.Timber;

/**
 * Created by ADMIN on 05-12-2017.
 */

public class EmailApi {
    private OkHttpClient client;

    public EmailApi(OkHttpClient client) {

        this.client = client;
    }

    public void sendEmail(String body,String emailUrl) {
        Timber.d("Email body-->:  %s Email url-->:  %s client hashCode:-->  %s ",body ,emailUrl,client.toString());
    }
}
