package com.basic.xy.smartbulter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basic.xy.smartbulter.R;
import com.basic.xy.smartbulter.entity.MyUser;
import com.basic.xy.smartbulter.util.L;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class ForgetPasswordActivity extends AppCompatActivity {
    private EditText mFindPwdEmailET,mOriginalPwdET,mNewPwdET,mConfirmPwdET;
    private Button mFindPwdBtn,mChangePwdBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        initView();
    }

    private void initView() {
        mFindPwdEmailET = (EditText) findViewById(R.id.et_find_pwd_email);
        mOriginalPwdET = (EditText) findViewById(R.id.et_original_pwd);
        mNewPwdET = (EditText) findViewById(R.id.et_new_pwd);
        mConfirmPwdET = (EditText) findViewById(R.id.et_confirm_pwd);

        mFindPwdBtn = (Button) findViewById(R.id.btn_find_pwd);
        mFindPwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findPwdByEmail();
            }
        });
        mChangePwdBtn = (Button) findViewById(R.id.btn_change_pwd);
        mChangePwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePwd();
            }
        });
    }

    //通过旧密码修改密码
    private void changePwd() {
        String originalPwd = mOriginalPwdET.getText().toString().trim();
        String newPwd = mNewPwdET.getText().toString().trim();
        String confirmPwd = mConfirmPwdET.getText().toString().trim();
        if (!(TextUtils.isEmpty(originalPwd) & TextUtils.isEmpty(newPwd) & TextUtils.isEmpty(confirmPwd))) {
            if (newPwd.equals(confirmPwd)) {
                //将密码传入后台进行验证修改
                MyUser.updateCurrentUserPassword(originalPwd, confirmPwd, new UpdateListener() {

                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            Toast.makeText(ForgetPasswordActivity.this, "密码修改成功，可以用新密码进行登录啦", Toast.LENGTH_SHORT).show();
                        }else{
                            if (e.getErrorCode() == 210) {
                                Toast.makeText(ForgetPasswordActivity.this, "旧密码不正确", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(ForgetPasswordActivity.this, "密码修改失败", Toast.LENGTH_SHORT).show();
                            L.e(e.toString());
                        }
                    }

                });
            } else {
                Toast.makeText(this, "两次输入的新密码不一致", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "输入不能为空", Toast.LENGTH_SHORT).show();
        }

    }

    //通过验证邮箱修改密码
    private void findPwdByEmail() {
        final String email = mFindPwdEmailET.getText().toString().trim();
        BmobUser.resetPasswordByEmail(email, new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(ForgetPasswordActivity.this, "重置密码请求成功，请到" + email + "邮箱进行密码重置操作", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ForgetPasswordActivity.this, "重置密码失败", Toast.LENGTH_SHORT).show();
                    L.e(e.toString());
                }
            }
        });
    }
}
