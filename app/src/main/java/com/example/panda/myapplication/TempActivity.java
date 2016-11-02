package com.example.panda.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Objects;

public class TempActivity extends AppCompatActivity {
    FragmentTransaction transaction = getSupportFragmentManager()
            .beginTransaction();
    private int count = 0;
    private ArrayList<Object> list = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp);
        FirstFragment firstFragment = new FirstFragment();
        SecondFragment secondFragment = new SecondFragment();
        ThirdFragment thirdFragment = new ThirdFragment();
        FourFragment fourFragment = new FourFragment();
        list.add(firstFragment);
        list.add(secondFragment);
        list.add(thirdFragment);
        list.add(fourFragment);

        if (getIntent().getStringExtra("num").equals("1")) {
            count = 1;
            transaction.replace(R.id.main_container, firstFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        if (getIntent().getStringExtra("num").equals("2")) {
            count = 2;

            transaction.replace(R.id.main_container, secondFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        if (getIntent().getStringExtra("num").equals("3")) {
            count = 3;
            transaction.replace(R.id.main_container, thirdFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        if (getIntent().getStringExtra("num").equals("4")) {
            count = 4;
            transaction.replace(R.id.main_container, fourFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }




}
