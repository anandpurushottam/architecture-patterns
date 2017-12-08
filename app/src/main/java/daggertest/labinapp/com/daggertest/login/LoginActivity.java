package daggertest.labinapp.com.daggertest.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import daggertest.labinapp.com.daggertest.R;
import daggertest.labinapp.com.daggertest.base.BaseActivity;
import daggertest.labinapp.com.daggertest.login.di.DaggerLoginComponent;
import daggertest.labinapp.com.daggertest.login.di.LoginComponent;
import daggertest.labinapp.com.daggertest.login.di.LoginModule;
import timber.log.Timber;

/**
 * Created by ADMIN on 06-12-2017.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View, GoogleApiClient.OnConnectionFailedListener {

    public static final int RC_SIGN_IN = 9001;
    @Inject
    LoginPresenter mPresenter;
    @Inject
    GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ButterKnife.bind(this);
        googleApiClient.connect();
    }


    @OnClick(R.id.googleLogin)
    public void googleLogin() {
        if (googleApiClient != null) {

            if (googleApiClient.isConnected())
                googleApiClient.clearDefaultAccountAndReconnect();

            startActivityForResult(Auth.GoogleSignInApi.getSignInIntent(googleApiClient), RC_SIGN_IN);
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                showProgressDialog();
                mPresenter.logInWithFirebase(result.getSignInAccount());
            }
        }
    }


    @Override
    public void startNextActivity() {
        hideProgressDialog();
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFirebaseAuthenticationFailedMessage(String error) {
        hideProgressDialog();
        Toast.makeText(this, "Error: " + error, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.takeView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();  //prevent leaking activity in
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Timber.e("onConntectionFailed %s", connectionResult.getErrorMessage());
    }

    @Override
    protected void setupActivityComponent() {
        LoginComponent loginComponent = DaggerLoginComponent.builder()
                .loginModule(new LoginModule(this))
                .build();
        loginComponent.inject(this);

    }
}
