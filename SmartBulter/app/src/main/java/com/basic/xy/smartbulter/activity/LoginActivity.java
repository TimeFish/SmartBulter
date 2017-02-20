package com.basic.xy.smartbulter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basic.xy.smartbulter.MainActivity;
import com.basic.xy.smartbulter.R;
import com.basic.xy.smartbulter.entity.MyUser;
import com.basic.xy.smartbulter.util.L;
import com.basic.xy.smartbulter.view.CustomDialog;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class LoginActivity extends AppCompatActivity {
    private EditText mUsernameET,mPasswordET;
    private CheckBox mRemPwdCB;
    private Button mLoginBtn,mRegisterBtn;
    private TextView mForgetPwdTV;
    private CustomDialog customDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

    }

    private void initView() {
        mRegisterBtn = (Button) findViewById(R.id.btn_register);
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        mLoginBtn = (Button) findViewById(R.id.btn_login);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogin();
            }
        });
        mUsernameET = (EditText) findViewById(R.id.et_login_username);
        mPasswordET = (EditText) findViewById(R.id.et_login_password);
        mRemPwdCB = (CheckBox) findViewById(R.id.cb_rember_pwd);

        mRemPwdCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onCheckedRemberPwd();
            }
        });

        mForgetPwdTV = (TextView) findViewById(R.id.tv_forget_pwd);
        mForgetPwdTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgetPasswordActivity.class));
            }
        });

        customDialog = new CustomDialog(this, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, R.layout.dialog_loading, R.style.Theme_dialog, Gravity.CENTER, R.style.pop_anim_style);
        customDialog.setCancelable(false);
    }


    private void onCheckedRemberPwd() {
    }


    private void onLogin() {
        String username = mUsernameET.getText().toString().trim();
        String password = mPasswordET.getText().toString().trim();

        if (!(TextUtils.isEmpty(username) & TextUtils.isEmpty(password))) {
            customDialog.show();
            //登录
            BmobUser.loginByAccount(username, password, new LogInListener<MyUser>() {

                @Override
                public void done(MyUser user, BmobException e) {
                    customDialog.dismiss();
                    if (user != null) {
                        //目前Bmob后台不具有邮箱未验证限制登录功能，需要自己在前台验证
                        if (user.getEmailVerified()) {
                            L.i("用户登录成功");
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "请前往邮箱验证", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        L.e(e.toString());
                    }
                }
            });
        } else {
            Toast.makeText(this, R.string.warn_input_null, Toast.LENGTH_SHORT).show();
        }
    }
}
