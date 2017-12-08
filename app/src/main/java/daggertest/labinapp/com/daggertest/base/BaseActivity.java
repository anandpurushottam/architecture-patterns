package daggertest.labinapp.com.daggertest.base;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import dagger.android.support.DaggerAppCompatActivity;
import daggertest.labinapp.com.daggertest.R;

public abstract class BaseActivity extends AppCompatActivity {
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog b;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();
    }

    protected abstract void setupActivityComponent();


    protected void showProgressDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.progress_dialog_layout, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);

        b = dialogBuilder.create();
        b.setCanceledOnTouchOutside(false);
        b.show();
    }

    protected void hideProgressDialog() {
        if (b != null && b.isShowing()) {
            b.dismiss();
        }


    }


}
