package com.example.movietime.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movietime.R;
import com.example.movietime.interfaces.LoginView;
import com.example.movietime.ui.movieList.MovieList;

import es.dmoral.toasty.Toasty;


public class Login extends AppCompatActivity implements View.OnClickListener, LoginView {
    private EditText username, password;
    private LoginViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        model = new LoginViewModel(this);
        initViews();
    }
    private void initViews() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        findViewById(R.id.login).setOnClickListener(this);
        findViewById(R.id.register).setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        String usernameString, passowrdString;
        switch (view.getId()) {
            case R.id.login:
                usernameString = username.getText().toString();
                passowrdString = password.getText().toString();
                model.login(usernameString, passowrdString);
                break;
            case R.id.register:
                usernameString = username.getText().toString();
                passowrdString = password.getText().toString();
                model.register(usernameString, passowrdString);
                break;
        }
    }

    @Override
    public void tryAgainToast() {
        Toasty.error(this, "TryAgain", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void RegistrationSucess() {
        Toasty.success(this, "Registration success", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void startMovieListActivity(Boolean isAdmin) {
        Intent intent = new Intent(this, MovieList.class);
        intent.putExtra("isAdmin",isAdmin);
        startActivity(intent);
    }
}