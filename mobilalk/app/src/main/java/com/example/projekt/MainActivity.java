package com.example.projekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
{
    private static final String LOG_TAG = MainActivity.class.getName();
    private static final int SECRET_KEY = 99;
    private FirebaseAuth auth;

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);

        auth = FirebaseAuth.getInstance();
    }

    public void login(View view)
    {
        String usernamestr = username.getText().toString();
        String passwordstr = password.getText().toString();
        if (usernamestr.equals("") || passwordstr.equals(""))
        {
            return;
        }
        auth.signInWithEmailAndPassword(usernamestr, passwordstr).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    startShopping();
                }
                else Toast.makeText(MainActivity.this, "Nem sikerült a bejelentkezés: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Log.i(LOG_TAG, "login:" + username + ", pw: "+ password);
    }

    private void startShopping()
    {
        Intent intent = new Intent(this, ShopPageActivity.class);
        startActivity(intent);
    }

    public void register(View view)
    {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }
}