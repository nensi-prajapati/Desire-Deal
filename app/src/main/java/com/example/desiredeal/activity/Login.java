package com.example.desiredeal.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desiredeal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText memail, mpassword;
    Button Signup1, Login1;

    FirebaseAuth fAuth;

    TextView forgotpass;

    private ProgressDialog progressDialog;
    private boolean loginSuccessful = false;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        memail = findViewById(R.id.Username1);
        mpassword = findViewById(R.id.password1);
        Login1 = findViewById(R.id.login1);
        Signup1 = findViewById(R.id.signup1);
        forgotpass = findViewById(R.id.Forgotpass);


        fAuth = FirebaseAuth.getInstance();


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Login in...");
        progressDialog.setCancelable(false);

        Signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Signup.class));
                onBackPressed();
            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Forgotpassword.class);
                startActivity(intent);

            }
        });

        Login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check if progressDialog is already showing, prevent multiple clicks
                if (progressDialog.isShowing()) {
                    return;
                }

                progressDialog.show();

                String Username1 = memail.getText().toString().trim();
                String password1 = mpassword.getText().toString().trim();

                // Check if both email and password fields are filled in
                if (TextUtils.isEmpty(Username1) || TextUtils.isEmpty(password1)) {
                    Toast.makeText(Login.this, "Please fill in both email and password fields",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password1.length() < 6) {
                    mpassword.setError("Password must be >= 6 character");
                    return;
                }



                fAuth.signInWithEmailAndPassword(Username1, password1)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // User is already registered with this email and password combination
                                SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putBoolean("login", true);
                                editor.apply();

                                progressDialog.dismiss();

                                FirebaseUser user = fAuth.getCurrentUser();
                                assert user != null;
                                Log.d(TAG, "User Logged in" + user.getEmail());
                                Toast.makeText(Login.this, "Login Successful",
                                        Toast.LENGTH_SHORT).show();
                                loginSuccessful = true; // Set login flag to true
                                startMainActivity();
                            } else {
                                // User is not registered with this email and password combination
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(Login.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        });

            }
        });

    }


    private void startMainActivity() {
        // Login is successful, start the MainActivity
        Intent intent = new Intent(Login.this, MainActivity.class);
     //   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed() {
        if (loginSuccessful) {
            // If login was successful, exit the app
            finishAffinity();
        } else {
            // If login was not successful, go back to the welcome screen
            super.onBackPressed();
        }
    }
}




