package com.example.panda.myapplication.Ui.Activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.panda.myapplication.Ui.Fragment.FirstFragment;
import com.example.panda.myapplication.Ui.Fragment.FourFragment;
import com.example.panda.myapplication.R;
import com.example.panda.myapplication.Ui.Fragment.SecondFragment;
import com.example.panda.myapplication.Ui.Fragment.ThirdFragment;

import java.util.ArrayList;

public class TempActivity extends AppCompatActivity {
    FragmentTransaction transaction = getSupportFragmentManager()
            .beginTransaction();
    private int count = 0;
    private ArrayList<Object> list = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp);
        FirstFragment firstFragment = FirstFragment.newInstance();
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
            transaction.show(firstFragment);
        }
        if (getIntent().getStringExtra("num").equals("2")) {
            count = 2;

            transaction.replace(R.id.main_container, secondFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            transaction.show(secondFragment);
        }
        if (getIntent().getStringExtra("num").equals("3")) {
            count = 3;
            transaction.replace(R.id.main_container, thirdFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            transaction.show(thirdFragment);
        }
        if (getIntent().getStringExtra("num").equals("4")) {
            count = 4;
            transaction.replace(R.id.main_container, fourFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            transaction.show(fourFragment);
        }
    }




}
