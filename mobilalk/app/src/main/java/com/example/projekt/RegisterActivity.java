package com.example.projekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity
{
    private static final String LOG_TAG = RegisterActivity.class.getName();

    EditText username;
    EditText password;
    EditText pwconfirm;
    EditText teljesnev;
    EditText email;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Bundle bundle = getIntent().getExtras();
        int secret_key = getIntent().getIntExtra("SECRET_KEY", 0);

        if (secret_key != 99) finish();

        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        pwconfirm = findViewById(R.id.editTextPasswordConfirm);
        teljesnev = findViewById(R.id.editTextTeljesnev);
        email = findViewById(R.id.editTextEmail);

        auth = FirebaseAuth.getInstance();

    }

    private void startShopping()
    {
        Intent intent = new Intent(this, ShopPageActivity.class);
        startActivity(intent);
    }

    public void back(View view)
    {
        finish();
    }

    public void register(View view)
    {
        String name = username.getText().toString();
        String em = email.getText().toString();
        String pw = password.getText().toString();
        String pwc = pwconfirm.getText().toString();
        if (name.equals("") || em.equals("") || pw.equals("") || pwc.equals(""))
        {
            return;
        }
        if (!pwc.equals(pw))
        {
            return;
        }
        auth.createUserWithEmailAndPassword(em, pw).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful()) startShopping();
                else
                {
                    Toast.makeText(RegisterActivity.this, "Nem sikerült a regisztráció: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}