package com.example.panda.myapplication;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.umeng.message.PushAgent;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import static anetwork.channel.http.NetworkSdkSetting.context;


public class enroll extends Activity {
    private Button enroll_button;
    private Spinner spinner1;
    private Spinner spinner2;
    private static EditText Name_list;
    private static EditText Student_id_list;
    private static EditText Personal_id_list;
    private static final String[] value1 = {"male", "female"};
    private static final String[] value2 = {"线路1", "线路2","线路3","线路4"};
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;
    private static String sex;
    private static String line;
    public static int cardNumber1;
    public static int cardNumber2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);
        PushAgent.getInstance(context).onAppStart();
        Bmob.initialize(this, "b2a75d2c36f8166500b4c27832a78bb8");
        enroll_button = (Button) findViewById(R.id.enroll_button);
        spinner1 = (Spinner) findViewById(R.id.enroll_spinner1);
        spinner2 = (Spinner) findViewById(R.id.enroll_spinner2);
        Name_list=(EditText)findViewById(R.id.name);
        Student_id_list=(EditText)findViewById(R.id.student_id);
        Personal_id_list=(EditText)findViewById(R.id.personal_id);
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, value1);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, value2);
        //将adapter 添加到spinner中
        spinner1.setAdapter(adapter1);

        //添加事件Spinner事件监听
       spinner1.setOnItemSelectedListener(new SpinnerSelectedListener1());

        //设置默认值
        spinner1.setVisibility(View.VISIBLE);
        spinner2.setAdapter(adapter2);

        //添加事件Spinner事件监听
        spinner2.setOnItemSelectedListener(new SpinnerSelectedListener2());





        //设置默认值
        spinner2.setVisibility(View.VISIBLE);
        enroll_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                        String Name=Name_list.getText().toString();
                        String Student_id=Student_id_list.getText().toString();
                        String Personal_id=Personal_id_list.getText().toString();
                        enroll_list traveler=new enroll_list();
                        traveler.setName(Name);
                        traveler.setStudent_id(Student_id);
                        traveler.setSex(value1[cardNumber1]);
                        traveler.setLine(value2[cardNumber2]);
                        traveler.setPersonal_id(Personal_id);
                        traveler.save(new SaveListener<String>() {
                            @Override
                            public void done(String objectId,BmobException e) {
                                if(e==null){
                                    Toast.makeText(enroll.this, "success submit", Toast.LENGTH_SHORT).show();
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e1) {
                                        e1.printStackTrace();
                                    }

                                }else{
                                    Toast.makeText(enroll.this, "submit failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

        });
    }


    /**
     * Created by panda on 2016/7/27.
     */

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
    private class SpinnerSelectedListener1 implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            cardNumber1=i;
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }



    private class SpinnerSelectedListener2 implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            cardNumber2=i;
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

}