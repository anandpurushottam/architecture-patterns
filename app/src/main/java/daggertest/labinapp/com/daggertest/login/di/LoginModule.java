package daggertest.labinapp.com.daggertest.login.di;

import android.support.annotation.VisibleForTesting;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import dagger.Module;
import dagger.Provides;
import daggertest.labinapp.com.daggertest.R;
import daggertest.labinapp.com.daggertest.login.LoginActivity;
import daggertest.labinapp.com.daggertest.login.LoginPresenter;

/**
 * Created by ADMIN on 06-12-2017.
 */
@Module
public class LoginModule {

    private LoginActivity loginActivity;


    @Provides
    LoginPresenter provideLoginPresenter() {
        return new LoginPresenter(loginActivity);
    }


    public LoginModule(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }



    @Provides
    GoogleApiClient providesGoogleApiClient(GoogleSignInOptions googleSignInOptions) {
        return new GoogleApiClient.Builder(loginActivity)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }

    @Provides
    GoogleSignInOptions googleSignInOptions() {
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(loginActivity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
    }

}
