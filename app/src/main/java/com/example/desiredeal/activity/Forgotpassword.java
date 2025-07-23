package com.example.desiredeal.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.desiredeal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgotpassword extends AppCompatActivity {

    EditText memail;
    Button login1;

    FirebaseAuth fAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        login1 = findViewById(R.id.login1);
        memail = findViewById(R.id.memail);

        fAuth = FirebaseAuth.getInstance();

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = memail.getText().toString().trim();
                if (!TextUtils.isEmpty(email)) {
                    forgotpass(email);
                } else {
                    Toast.makeText(Forgotpassword.this, "Email Required!", Toast.LENGTH_SHORT).show();
                }

            }

            private void forgotpass(String email) {

                fAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override


                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Forgotpassword.this, "password reset email sent", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Forgotpassword.this, "Failed to send password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(Forgotpassword.this, R.color.pink));
        }


    }
}