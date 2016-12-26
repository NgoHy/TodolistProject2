package com.example.admin.todolistproject;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by Admin on 26/12/2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());

        if (getToolbar() != null) {
            setSupportActionBar(getToolbar());
        }
    }
    public void setToolbarTitle(@StringRes int title) {
        getToolbar().setTitle(title);
    }
    public void setBackButtonEnabled(boolean enabled) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(enabled);
            getSupportActionBar().setHomeButtonEnabled(enabled);
        }
    }

    public void setOnClickBackButton(View.OnClickListener listener) {
        getToolbar().setNavigationOnClickListener(listener);
    }

    protected abstract int getLayoutResource();
}
