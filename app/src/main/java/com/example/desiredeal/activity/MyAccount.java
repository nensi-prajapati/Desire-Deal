package com.example.desiredeal.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.desiredeal.R;
import com.example.desiredeal.adapter.MyAccountRecyclerAdapter;
import com.example.desiredeal.model.MyAccountRecyclerModel;
import com.example.desiredeal.model.UserDetailModel;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.litepal.LitePal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class MyAccount extends AppCompatActivity {

    ImageView imageView;
    ImageView addphoto;

    TextView save;
    private RecyclerView addressRecyclerView;
    private MyAccountRecyclerAdapter myAccountRecyclerAdapter;
    private List<MyAccountRecyclerModel> addressList;


    private EditText mPhoneEditText;
    UserDetailModel userDetailModel;

    private EditText username,username1,userphonenumber;
    private ImageView userimage;
    private boolean mShowCheckbox = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        imageView = findViewById(R.id.uer_image);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String email = currentUser.getEmail();
            EditText emailTextView = findViewById(R.id.edit_text_email);
            emailTextView.setText(email);
            emailTextView.setEnabled(false); // disable editing
        }


        addphoto = findViewById(R.id.addphoto);

        addphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(MyAccount.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });


        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set the back arrow color to white
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_left_foreground);

        // Enable the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        addressList = LitePal.findAll(MyAccountRecyclerModel.class);

        ImageView addAddressButton = findViewById(R.id.AddAddress);
        addAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyAccount.this, Address.class));
                onBackPressed();
            }
        });

        addressRecyclerView = findViewById(R.id.Address_recycler_view);
        myAccountRecyclerAdapter = new MyAccountRecyclerAdapter((ArrayList<MyAccountRecyclerModel>) addressList, mShowCheckbox);
        addressRecyclerView.setAdapter(myAccountRecyclerAdapter);
        addressRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(MyAccount.this, R.color.pink));
        }

        // Check if there are multiple addresses available and set the mShowCheckbox variable accordingly
        if (addressList.size() > 1) {
            mShowCheckbox = true;
        }

        // Set the adapter's showCheckbox variable
        myAccountRecyclerAdapter.setShowCheckbox(mShowCheckbox);

        myAccountRecyclerAdapter.setOnItemClickListener(new MyAccountRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MyAccountRecyclerModel model) {
                Intent intent = new Intent(MyAccount.this, Address.class);
                intent.putExtra("data", model);
                startActivity(intent);
            }

            @Override
            public void onRemoveClick(int position) {
                // Get the item that was clicked
                MyAccountRecyclerModel item = addressList.get(position);

                // Delete the item from the database
                LitePal.delete(MyAccountRecyclerModel.class, item.getId());

                // Remove the item from the data list
                addressList.remove(position);

                // Notify the adapter that the item has been removed
                myAccountRecyclerAdapter.notifyItemRemoved(position);
            }
        });

        username = findViewById(R.id.edit_text_1);
        username1 = findViewById(R.id.edit_text);
        userphonenumber = findViewById(R.id.edit_text_2);
        userimage = findViewById(R.id.uer_image);



        save = findViewById(R.id.savedetails);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString().trim();
                String name1 = username1.getText().toString().trim();
                String phonenumber = userphonenumber.getText().toString().trim();
                String imagepath = userDetailModel.getImagepath();

                // Create a new UserDetailModel instance

                if (userDetailModel != null) {
                    // If userDetailModel is not null, update the details
                    updateDetails();
                    Toast.makeText(MyAccount.this, "Details updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // If userDetailModel is null, create a new instance and save the details
                    userDetailModel = new UserDetailModel(name, name1, phonenumber,imagepath);
                    userDetailModel.save();
                    Toast.makeText(MyAccount.this, "Your Details is Added", Toast.LENGTH_SHORT).show();
                }

                showDetails(name, name1, phonenumber,imagepath);

            }
        });

        // Retrieve and display the saved details (if any)
        userDetailModel = LitePal.findFirst(UserDetailModel.class);
        if (userDetailModel != null) {
            showDetails(userDetailModel.getName(), userDetailModel.getName2(), userDetailModel.getPhonenumber(),userDetailModel.getImagepath());
        }



    }

    private void saveImageToStorage(Uri uri) {
        String directoryPath = getExternalFilesDir(null).getAbsolutePath() + "/MyApp/Images/";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = "my_image.jpg";
        File destFile = new File(directory, fileName);

        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            OutputStream outputStream = new FileOutputStream(destFile);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            // Close the streams
            inputStream.close();
            outputStream.close();

            // Create a new UserDetailModel instance if it is null
            if (userDetailModel == null) {
                userDetailModel = new UserDetailModel();
            }

            // Save the image path to the UserDetailModel instance
            String imagePath = destFile.getAbsolutePath();
            userDetailModel.setImagepath(imagePath);
            userDetailModel.save();

            // Show a success message or perform any other action
            Toast.makeText(this, "Image saved successfully", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
            // Handle any errors that occur during the file copy process
        }
    }

    private void showDetails(String name, String name1, String phonenumber, String imagepath) {
        username.setText(name);
        username1.setText(name1);
        userphonenumber.setText(phonenumber);

        if (imagepath != null) {
            File imageFile = new File(imagepath);
            if (imageFile.exists()) {
                Glide.with(this)
                        .load(imageFile)
                        .into(userimage);
            }
        }
    }
    private void updateDetails() {
        String name = username.getText().toString().trim();
        String name1 = username1.getText().toString().trim();
        String phonenumber = userphonenumber.getText().toString().trim();
        String imagepath = userDetailModel.getImagepath();

        // Update the existing UserDetailModel object
        userDetailModel.setName(name);
        userDetailModel.setName2(name1);
        userDetailModel.setPhonenumber(phonenumber);
        userDetailModel.setImagepath(imagepath);
        userDetailModel.save();

        // Display a toast or perform any other action to indicate successful update

    }


    //  detaillist = LitePal.findAll(userimagempdel.class);

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            saveImageToStorage(uri);
            Glide.with(this)
                    .load(uri)
                    .into(userimage);

            // Update the userDetailModel with the new image path
            if (userDetailModel != null) {
                String imagePath = getExternalFilesDir(null).getAbsolutePath() + "/MyApp/Images/my_image.jpg";
                userDetailModel.setImagepath(imagePath);
                userDetailModel.save();
            }
        }
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