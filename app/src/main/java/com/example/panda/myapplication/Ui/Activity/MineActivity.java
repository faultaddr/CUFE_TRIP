package com.example.panda.myapplication.Ui.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.panda.myapplication.Constants;
import com.example.panda.myapplication.R;

public class MineActivity extends AppCompatActivity {
    private String s;
    private Receiver myBroadcastReceiver;
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        textView=(TextView)findViewById(R.id.my_email_name);
        imageView=(ImageView)findViewById(R.id.exit);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MineActivity.this,LoginActivity.class);
                intent.setAction("ExitRelogin");
                startActivity(intent);
                finish();
                finishActivity(101);
            }
        });
        textView.setText(Constants.userName);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            //actionBar.setDisplayHomeAsUpEnabled(true);
            //actionBar.setDisplayShowHomeEnabled(true);
            //actionBar.setLogo(R.mipmap.ic_launcher);
            //actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setTitle("我的");
        }
        myBroadcastReceiver = new Receiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.panda");
        registerReceiver(myBroadcastReceiver, filter);




        unregisterReceiver(myBroadcastReceiver);


    }
    class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            s= intent.getExtras().getString("name");
            Log.i("Recevier1", "接收到:"+s);
            textView=(TextView)findViewById(R.id.my_email_name);
            textView.setText(s);
        }

    }
}
