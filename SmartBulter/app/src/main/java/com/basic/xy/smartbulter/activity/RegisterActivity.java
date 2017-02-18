package com.basic.xy.smartbulter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.basic.xy.smartbulter.R;
import com.basic.xy.smartbulter.entity.MyUser;
import com.basic.xy.smartbulter.util.L;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {
    private EditText mUsernameET;
    private EditText mPasswordET;
    private EditText mConfirmPwsET;
    private EditText mAgeET;
    private EditText mDescET;
    private RadioGroup mGenderRG;
    private EditText mEmail;
    private boolean isMan = true;
    private Button mRegisterBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        mUsernameET = (EditText) findViewById(R.id.et_username);
        mPasswordET = (EditText) findViewById(R.id.et_pwd);
        mConfirmPwsET = (EditText) findViewById(R.id.et_confirm_pwd);
        mAgeET = (EditText) findViewById(R.id.et_age);
        mDescET = (EditText) findViewById(R.id.et_intro);
        mGenderRG = (RadioGroup) findViewById(R.id.rg_gender);
        mEmail = (EditText) findViewById(R.id.et_email);
        mRegisterBtn = (Button) findViewById(R.id.btn_register);
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegister();
            }
        });
    }

    private void onRegister() {
        String username = mUsernameET.getText().toString().trim();
        String password = mPasswordET.getText().toString().trim();
        String confirmPwd = mConfirmPwsET.getText().toString().trim();
        String age = mAgeET.getText().toString().trim();
        String desc = mDescET.getText().toString().trim();
        String email = mEmail.getText().toString().trim();
        isRegistered();
        if (!(TextUtils.isEmpty(username) & TextUtils.isEmpty(password) & TextUtils.isEmpty(confirmPwd) & TextUtils.isEmpty(age) & TextUtils.isEmpty(email) )) {
            if (password.equals(confirmPwd)) {
                //获取简介
                if (TextUtils.isEmpty(desc)) {
                    desc = "这个人很懒，什么都没留下";
                }
                //获取性别
                mGenderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.rb_man) {
                            isMan = true;
                        } else {
                            isMan = false;
                        }
                    }
                });
                //注册
                MyUser user = new MyUser();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.setAge(Integer.parseInt(age));
                user.setGender(isMan ? "男" : "女");
                user.setDesc(desc);
                //注意：不能用save方法进行注册
                user.signUp(new SaveListener<MyUser>() {
                    @Override
                    public void done(MyUser s, BmobException e) {
                        if (e == null) {
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this,e.toString(), Toast.LENGTH_SHORT).show();
                            L.e(e.toString());
                        }
                    }
                });
            } else {
                Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "输入不可为空", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 检测该用户名的用户是否已经注册
     */
    private void isRegistered() {
    }
}
