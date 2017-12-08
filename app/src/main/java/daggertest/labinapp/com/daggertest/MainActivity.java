package daggertest.labinapp.com.daggertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
  /*  @Inject
    Email email;
    @Inject
    EmailApi emailApi;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     /*   ((DaggerApp) getApplication()).getEmailComponent().inject(this);

        email.send("first body");*/

    }

    public void doSomething(View view) {
        // startActivity(new Intent(this, SecondActivity.class));

    }
}
