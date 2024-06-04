package com.example.cursach;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Авторизация");
        }

        btn = findViewById(R.id.btn_enter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputLayout ti = findViewById(R.id.input_login);
                String login = ti.getEditText().getText().toString();
                ti = findViewById(R.id.input_password);
                String pass = ti.getEditText().getText().toString();
                Intent intent = new Intent();
                if (login.equals("admin") && pass.equals("123")){
                    intent.putExtra("profile", "admin");
                    setResult(RESULT_OK, intent);
                    finish();
                } else if (login.equals("user") && pass.equals("123")) {
                    intent.putExtra("profile", "user");
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    TextView tv = findViewById(R.id.login_warn);
                    tv.setText("Неверный логин или пароль");
                }
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}