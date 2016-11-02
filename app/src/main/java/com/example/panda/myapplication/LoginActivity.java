package com.example.panda.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.umeng.message.PushAgent;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadBatchListener;

import static android.Manifest.permission.READ_CONTACTS;
import static anetwork.channel.http.NetworkSdkSetting.context;

/**
 * A login screen that offers login via email/password.
 */
/*

TODO:- 我们在这里可以使用sqlite简单的数据库，进行用户查询，只要用户在这台手机上登录过
TODO:- 那么我们就可以把用户名密码存到本地的数据库，在登录时，我们首先在本地进行查询，如果
TODO:- 发现没有相应的用户，再从bmob端数据库获取相关信息。


 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;
    public static boolean bool=false;
    public static boolean record=false;
    private UserLoginTask mAuthTask = null;
    private int i;
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    final Lock lock = new ReentrantLock();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //PushAgent.getInstance(context).onAppStart();

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        //populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        DatabaseHelper database = new DatabaseHelper(LoginActivity.this);//这段代码放到Activity类中才用this
        SQLiteDatabase db1 = null;
        db1 = database.getReadableDatabase();
        try {
            //创建SharedPreferences对象
            SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);

            //获得保存在SharedPredPreferences中的用户名和密码
            String user_name = sp.getString("username", "");
            String pass_word = sp.getString("password", "");

            if (user_name.length()>0){
                mEmailView.setText(user_name);
                mPasswordView.setText(pass_word);
            }
            //在用户名和密码的输入框中显示用户名和密码

        }catch(Exception excepton){

        }
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        assert mEmailSignInButton != null;
        Button mEmailLoginButton = (Button) findViewById(R.id.email_register_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                record=false;
                Log.i("record",""+record);
                attemptLogin();

            }
        });
        mEmailLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                record=true;
                Log.i("record",""+record);
                attemptLogin();
            }
        });
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }





                // Reset errors.
                mEmailView.setError(null);
                mPasswordView.setError(null);

                // Store values at the time of the login attempt.
                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();

                boolean cancel = false;
                View focusView = null;

                // Check for a valid password, if the user entered one.
                if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
                    mPasswordView.setError(getString(R.string.error_invalid_password));
                    focusView = mPasswordView;
                    cancel = true;
                }

                // Check for a valid email address.
                if (TextUtils.isEmpty(email)) {
                    mEmailView.setError(getString(R.string.error_field_required));
                    focusView = mEmailView;
                    cancel = true;
                } else if (!isEmailValid(email)) {
                    mEmailView.setError(getString(R.string.error_invalid_email));
                    focusView = mEmailView;
                    cancel = true;
                }

                if (cancel) {
                    // There was an error; don't attempt login and focus the first
                    // form field with an error.
                    focusView.requestFocus();
                } else {
                    // Show a progress spinner, and kick off a background task to
                    // perform the user login attempt.
                    //showProgress(true);

                    mAuthTask = new UserLoginTask(email, password);
                    mAuthTask.execute();


                }
            }



    /**
     * 判断是不是一个合法的电子邮件地址
     * @param email
     * @return
     */




    private boolean isEmailValid(String email) {
            if (email == null || email.length() < 5) {
                // #如果帐号小于5位，则肯定不可能为邮箱帐号eg: x@x.x
                return false;
            }
            if (!email.contains("@")) {// 判断是否含有@符号
                return false;// 没有@则肯定不是邮箱
            }
            String[] sAcc = email.split("@");
            if (sAcc.length != 2) {// # 数组长度不为2则包含2个以上的@符号，不为邮箱帐号
                return false;
            }
            if (sAcc[0].length() <= 0) {// #@前段为邮箱用户名，自定义的话至少长度为1，其他暂不验证
                return false;
            }
            if (sAcc[1].length() < 3 || !sAcc[1].contains(".")) {
                // # @后面为域名，位数小于3位则不为有效的域名信息
                // #如果后端不包含.则肯定不是邮箱的域名信息
                return false;
            } else {
                if (sAcc[1].substring(sAcc[1].length() - 1).equals(".")) {
                    // # 最后一位不能为.结束
                    return false;
                }
                String[] sDomain = sAcc[1].split("\\.");
                // #将域名拆分 tm-sp.com 或者 .com.cn.xxx
                for (String s : sDomain) {
                    if (s.length() <= 0) {
                        System.err.println(s);
                        return false;
                    }
                }

            }
            return true;
        }


    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 6;
    }

    /**
     * Shows the progress UI and hides the login form.
     */


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }


        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.



            Log.i("record1",""+record);

                        if (record == false)

                        {
                            new Thread(){
                            public void run() {

                                BmobUser.loginByAccount(mEmail, mPassword, new LogInListener<User>() {

                                    @Override
                                    public void done(User user, BmobException e) {
                                        if (user != null) {
                                            bool = true;

                                            Log.i("smile", "用户登陆成功" + bool);
                                        } else bool = false;
                                    }
                                });
                            }}.start();
                        }

                        if (record == true)

                        {
                            BmobUser bu = new BmobUser();
                            bu.setUsername(mEmail);
                            bu.setPassword(mPassword);
                            bu.setEmail(mEmail);
//注意：不能用save方法进行注册

                            bu.signUp(new SaveListener<User>() {
                                @Override
                                public void done(User s, BmobException e) {
                                    if (e == null) {
                                        Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();

                                        bool = false;
                                        Log.i("bool1", "" + bool);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "注册失败" + e.getMessage(), Toast.LENGTH_LONG).show();
                                        bool = false;
                                    }

                                }
                            });
                            BmobUser.requestEmailVerify(mEmail, new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        Log.i("memail", "" + mEmail);
                                        Toast.makeText(getApplicationContext(), "请求验证邮件成功，请到" + mEmail + "邮箱中进行激活。", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Log.i("memail", "" + mEmail);
                                        Log.i("e:", "" + e.getMessage());
                                        Toast.makeText(getApplicationContext(), "发送失败:" + e.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
            /*
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
*/
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i(">>>>>", ""+bool);


                return bool;

            }

        public void record(){

                SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);

                //获得sp的编辑器
                SharedPreferences.Editor ed = sp.edit();

                //以键值对的显示将用户名和密码保存到sp中
                ed.putString("username", mEmail);
                ed.putString("password", mPassword);

                //提交用户名和密码
                ed.apply();
                DatabaseHelper database = new DatabaseHelper(LoginActivity.this);//这段代码放到Activity类中才用this
                SQLiteDatabase db = null;
                db = database.getReadableDatabase();
                ContentValues cv = new ContentValues();//实例化一个ContentValues用来装载待插入的数据cv.put("username","Jack Johnson");//添加用户名
                cv.put("username",mEmail);
                cv.put("password",mPassword); //添加密码
                db.insert("user",null,cv);//执行插入操作
            }



        //if successfully post then finish ,else setError "this password is incorrect"
        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            //showProgress(false);

            if (success) {
                record();
                Intent intent = new Intent();

                intent.setClass(LoginActivity.this,Progress.class);

                startActivity(intent);

                finish();

            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

