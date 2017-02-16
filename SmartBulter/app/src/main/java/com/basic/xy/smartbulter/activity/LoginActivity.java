package com.basic.xy.smartbulter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.basic.xy.smartbulter.R;

public class LoginActivity extends AppCompatActivity {
    private EditText mUsernameET,mPasswordET;
    private CheckBox mRemPwdCB;
    private Button mLoginBtn,mRegisterBtn;
    private TextView mForgetPwdTV;
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
    }
}
