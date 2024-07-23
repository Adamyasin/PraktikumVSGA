package com.example.praktikumvsga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private Button buttonLogin;
    private EditText username, password;
    private TextView alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.txtUsername);
        password = findViewById(R.id.txtPassword);
        alert = findViewById(R.id.alert);
        alert.setVisibility(View.GONE);

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (user.equals("admin") && pass.equals("admin")) {
                    Intent intent = new Intent(LoginActivity.this, LoginSS.class);
                    startActivity(intent);
                } else {
                    alert.setVisibility(View.VISIBLE);
                    password.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                }
            }
        });
    }
}
