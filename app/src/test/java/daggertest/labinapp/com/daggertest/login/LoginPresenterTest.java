package daggertest.labinapp.com.daggertest.login;

import android.text.TextUtils;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ADMIN on 07-12-2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {
    @Mock
    LoginContract.View view;
    @Mock
    LoginActivity loginActivity;
    LoginPresenter presenter;
    @Mock
    FirebaseUser firebaseUser;
    @Mock
    TextUtils textUtils;
    @Mock
    GoogleSignInAccount googleSignInAccount;

    @Before
    public void setUp() throws Exception {
        presenter = new LoginPresenter(loginActivity);
        presenter.takeView(view);

    }

    @After
    public void tearDown() throws Exception {
        presenter.dropView();
    }

    @Test
    public void testLoginCompleted() throws Exception {
        view.showFirebaseAuthenticationFailedMessage("error");

    }


}