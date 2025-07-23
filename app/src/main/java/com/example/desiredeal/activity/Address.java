package com.example.desiredeal.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desiredeal.R;
import com.example.desiredeal.model.MyAccountRecyclerModel;
import com.google.android.material.appbar.MaterialToolbar;

public class Address extends AppCompatActivity {

    EditText nameEditText;
    EditText addressEditText;
    EditText addressSecondEditText;
    EditText stateEditText;
    EditText phoneNumberEditText;
    EditText ZipcodeEditText;
    MyAccountRecyclerModel myAccountRecyclerModel;


    String  phonePattern = "^[+]?[0-9]{10,13}$";
    boolean isUpdate = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set the back arrow color to white
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_left_foreground);

        // Enable the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameEditText = findViewById(R.id.name);
        addressEditText = findViewById(R.id.Addresss);
        addressSecondEditText = findViewById(R.id.address_second);
        stateEditText = findViewById(R.id.state);
        phoneNumberEditText = findViewById(R.id.phone_no);
        ZipcodeEditText = findViewById(R.id.zip_code);

        LinearLayout address = findViewById(R.id.AddAddress);

        address.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (checkValidation()) {

                    String name = nameEditText.getText().toString().trim();
                    String address_first = addressEditText.getText().toString().trim();
                    String address_second = addressSecondEditText.getText().toString().trim();
                    String state = stateEditText.getText().toString().trim();
                    String phoneNumberString = phoneNumberEditText.getText().toString().trim();
                    String zipcodeString = ZipcodeEditText.getText().toString().trim();

                    if (isUpdate) {

                        if (myAccountRecyclerModel != null) {
                            myAccountRecyclerModel.setName(name);
                            myAccountRecyclerModel.setAddres_first(address_first);
                            myAccountRecyclerModel.setAddress_second(address_second);
                            myAccountRecyclerModel.setPhonenumber(phoneNumberString);
                            myAccountRecyclerModel.setZip_code(zipcodeString);
                            myAccountRecyclerModel.update(myAccountRecyclerModel.getId());
                        }
                    } else {
                        // if myAccountRecyclerModel is null, it means user wants to add new address
                        myAccountRecyclerModel = new MyAccountRecyclerModel(name, address_first, address_second, state, phoneNumberString, zipcodeString);
                        myAccountRecyclerModel.save();
                    }

                    Intent intent = new Intent(Address.this, MyAccount.class);
                    startActivity(intent);
                    Toast.makeText(Address.this, "Data is successfully added/updated", Toast.LENGTH_SHORT).show();
                    onBackPressed();

                }

            }
        });

        Intent intent = getIntent();
        if (intent.hasExtra("data")) {
            isUpdate = true;

            TextView address_change = findViewById(R.id.address_change);
            address_change.setText("UPDATE ADDRESS");
            myAccountRecyclerModel = (MyAccountRecyclerModel) intent.getSerializableExtra("data");
            nameEditText.setText(String.valueOf(myAccountRecyclerModel.getName()));
            addressEditText.setText(myAccountRecyclerModel.getAddres_first());
            addressSecondEditText.setText(myAccountRecyclerModel.getAddress_second());
            stateEditText.setText(myAccountRecyclerModel.getState());
            ZipcodeEditText.setText(myAccountRecyclerModel.getZip_code());
            phoneNumberEditText.setText(String.valueOf(myAccountRecyclerModel.getPhonenumber()));

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(Address.this, R.color.pink));
        }

    }
    private boolean checkValidation() {
        if (TextUtils.isEmpty(nameEditText.getText().toString())) {
            Toast.makeText(Address.this, "All fields required!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(addressEditText.getText().toString())) {
            Toast.makeText(Address.this, "All fields required!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(addressSecondEditText.getText().toString())) {
            Toast.makeText(Address.this, "All fields required!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(stateEditText.getText().toString())) {
            Toast.makeText(Address.this, "All fields required!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(phoneNumberEditText.getText().toString())) {
            Toast.makeText(Address.this, "All fields required!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(ZipcodeEditText.getText().toString())) {
            Toast.makeText(Address.this, "All fields required!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            String phoneNumber = phoneNumberEditText.getText().toString().trim();
            if (!phoneNumber.matches(phonePattern)) {
                Toast.makeText(Address.this, "Invalid phone number format!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
