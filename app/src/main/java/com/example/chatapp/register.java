package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class register extends AppCompatActivity {
    TextView loginbtn;
    EditText username, email, password, confirmPassword;
    Button signup1;
    CircleImageView profile;
    Uri imageURI;
    String imageuri;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    ProgressDialog progressDialog;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Creating The Account");
        progressDialog.setCancelable(false);

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Initialize Firebase Database and Storage
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        loginbtn = findViewById(R.id.logintext);
        username = findViewById(R.id.rusername);
        email = findViewById(R.id.remail);
        password = findViewById(R.id.rpasswprd);
        confirmPassword = findViewById(R.id.rcpassword);
        signup1 = findViewById(R.id.singup);
        profile = findViewById(R.id.rptofile);

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this, LoginActivity.class);
                startActivity(intent);
//                finish();
            }
        });

        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                String confirmPasswordText = confirmPassword.getText().toString();
                String status = "Hey, I'm using this application";

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(emailText) ||
                        TextUtils.isEmpty(passwordText) || TextUtils.isEmpty(confirmPasswordText)) {
                    Toast.makeText(register.this, "Please enter valid information", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                } else if (!emailText.matches(emailPattern)) {
                    email.setError("Type a valid email here");
                    progressDialog.dismiss();
                } else if (passwordText.length() < 6) {
                    password.setError("Password must be 6 characters or more");
                    progressDialog.dismiss();
                } else if (!passwordText.equals(confirmPasswordText)) {
                    confirmPassword.setError("The passwords don't match");
                    progressDialog.dismiss();
                } else {
                    auth.createUserWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String id = task.getResult().getUser().getUid();
                                DatabaseReference reference = database.getReference().child("user").child(id);
                                StorageReference storageReference = storage.getReference().child("Upload").child(id);

                                if (imageURI != null) {
                                    storageReference.putFile(imageURI).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                    @Override
                                                    public void onSuccess(Uri uri) {
                                                        imageuri = uri.toString();
                                                        User user = new User(id, name, emailText, passwordText, imageuri, status);
                                                        reference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    progressDialog.show();
                                                                    Intent intent = new Intent(register.this, MainActivity.class);
                                                                    startActivity(intent);
//                                                                    finish();
                                                                } else {
                                                                    Toast.makeText(register.this, "Error in creating the user", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        }
                                    });
                                } else {
                                    String defaultImageUri = "https://firebasestorage.googleapis.com/v0/b/chatapp-91fb2.appspot.com/o/man.png?alt=media&token=aa9c329c-42a0-45d4-9f5a-5029a2105222";
                                    User user = new User(id, name, emailText, passwordText, defaultImageUri, status);
                                    reference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                progressDialog.show();
                                                Intent intent = new Intent(register.this, MainActivity.class);
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                Toast.makeText(register.this, "Error in creating the user", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            } else {
                                Toast.makeText(register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 10);
            }
        });
    }


}
