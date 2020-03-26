package com.dhanifudin.cashflow;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dhanifudin.cashflow.models.Session;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameInput;
    private EditText passwordInput;
    private Session session;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        //bind
        session = Application.getSession();

        usernameInput = findViewById(R.id.etUsername);
        passwordInput = findViewById(R.id.etPassword);

        if (session.isKeepUsername()){
            usernameInput.setText(session.getUsername());
        }
    }

    public void handleReset(View view){
        usernameInput.setText("");
        passwordInput.setText("");
    }

    public void handleLogin(View view){
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        boolean success = session.validate(username, password);
        if (success) {
            if (session.isKeepUsername()){
                session.setUsername(username);
            }
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Snackbar.make(view, "Authentication failed", Snackbar.LENGTH_SHORT).show();
        }
    }

}
