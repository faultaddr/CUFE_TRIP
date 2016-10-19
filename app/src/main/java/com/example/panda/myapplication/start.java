package com.example.panda.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.umeng.message.PushAgent;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class start extends AppCompatActivity implements View.OnClickListener {
    private Button button;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String path=null;
        super.onCreate(savedInstanceState);
        //PushAgent.getInstance(context).onAppStart();
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.initiate);
        button=(Button)findViewById(R.id.start_main);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        intent.setClass(this,LoginActivity.class);
        startActivity(intent);
        this.finish();
    }
}
