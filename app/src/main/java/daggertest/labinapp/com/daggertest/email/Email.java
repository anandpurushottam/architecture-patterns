package daggertest.labinapp.com.daggertest.email;

import daggertest.labinapp.com.daggertest.EmailApi.EmailApi;

/**
 * Created by ADMIN on 05-12-2017.
 */

public class Email {
    private String emailUrl;
    private EmailApi client;

    public Email(String emailUrl, EmailApi client) {
        this.emailUrl = emailUrl;
        this.client = client;
    }

    public boolean send(String body){
        client.sendEmail(body,emailUrl);
        return true;
    }
}
