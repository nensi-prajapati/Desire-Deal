package com.example.desiredeal.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.desiredeal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText username1,memail,mpassword,mphone;
    Button Signup1,Login1;
    CheckBox checkBox;

    FirebaseFirestore fstore;
    String userID;
    FirebaseAuth fAuth;
    private ProgressDialog progressDialog;
    String phonePattern = "^[+]?[0-9]{10,13}$";

    private boolean signupSuccessful = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username1 = findViewById(R.id.Username1);
        memail = findViewById(R.id.email);
        mphone = findViewById(R.id.phonenumber);
        mpassword = findViewById(R.id.password1);

        Signup1 = findViewById(R.id.signup1);
        Login1 = findViewById(R.id.login1);

        checkBox = findViewById(R.id.checkbox);




        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing up...");
        progressDialog.setCancelable(false);

        if (fAuth.getCurrentUser() != null && fAuth.getCurrentUser().isEmailVerified()) {
            // User is already signed in and email is verified, start MainActivity
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }


        Login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
                onBackPressed();

            }
        });

        Signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check if progressDialog is already showing, prevent multiple clicks
                if (progressDialog.isShowing()) {
                    return;
                }

                progressDialog.show();

                final String email = memail.getText().toString().trim();
                String password1 = mpassword.getText().toString().trim();
                final String Username1 = username1.getText().toString().trim();
                final String phonenumber = mphone.getText().toString().trim();

                if (TextUtils.isEmpty(Username1) || TextUtils.isEmpty(password1) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phonenumber)) {
                    Toast.makeText(Signup.this, "Please fill in all fields",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password1.length() < 6){
                    mpassword.setError("Password must be >= 6 character");

                }

                if(TextUtils.isEmpty(phonenumber)){
                    mphone.setError("Password is required");

                }

                ///////////////////////////////////////for phone number////////////////////////
                if (phonenumber.matches(phonePattern)) {
                    // Phone number is valid, save to Firestore
                    Map<String, Object> user = new HashMap<>();
                    user.put("phone", phonenumber);

                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("users").document("user1")
                            .set(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "User phone number saved to Firestore");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error saving user phone number to Firestore", e);
                                }
                            });
                } else {
                    // Phone number is not valid
                    mphone.setError("Invalid phone number");

                }

                ///////////////for checkbox/////////////////////////////

                if (checkBox.isChecked()) {
                    // Checkbox is selected, save to Firestore
                    Map<String, Object> user = new HashMap<>();
                    user.put("isAgreed", true);

                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("users").document("user1")
                            .set(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "User agreement saved to Firestore");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error saving user agreement to Firestore", e);
                                }
                            });
                } else {
                    // Checkbox is not selected
                    checkBox.setError("You must agree to the terms and conditions");
                    Toast.makeText(Signup.this, "You must agree to the terms and conditions", Toast.LENGTH_SHORT).show();

                }


                fAuth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){


                            FirebaseUser fuser = fAuth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    progressDialog.dismiss();
                                    Toast.makeText(Signup.this, "Register Successful", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Log.d(TAG,"Onfailure:Email Not Sent"+ e.getMessage());
                                }
                            });


                          //  Toast.makeText(Signup.this, "User Created", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("user").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("username",Username1);
                            user.put("email",email);
                            user.put("phone",phonenumber);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG,"onsucces: User profile is created for "+ userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"onFailure: " + e.toString());
                                }
                            });


                            SharedPreferences pref = getSharedPreferences("signup", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putBoolean("signup", true);
                            editor.apply();
                            signupSuccessful = true; // Set signup flag to true

                            startMainActivity();

                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(Signup.this, "Error"+task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }


        });


    }
    private void startMainActivity() {
        progressDialog.dismiss();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
     //   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
       finish(); // Finish the current activity to prevent going back to it
    }

    @Override
    public void onBackPressed() {
        if (signupSuccessful) {
            // If login was successful, exit the app
            finishAffinity();
        } else {
            // If login was not successful, go back to the welcome screen
            super.onBackPressed();
        }
    }


}