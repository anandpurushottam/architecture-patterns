package daggertest.labinapp.com.ui.login;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import daggertest.labinapp.com.data.source.remote.FirebaseUserService;
import daggertest.labinapp.com.data.source.remote.UserService;

import static org.mockito.Mockito.verify;

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
    FirebaseUserService firebaseUserService;
    @Mock
    UserService userService;



    @Before
    public void setUp() throws Exception {
        presenter = new LoginPresenter(loginActivity,firebaseUserService,userService, sessionService);
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