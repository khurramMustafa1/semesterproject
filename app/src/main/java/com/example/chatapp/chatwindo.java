package com.example.chatapp;//package com.example.chatapp;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//import java.util.Date;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
//public class chatwindo extends AppCompatActivity {
//
//
//    String reciverimg, reciverUid,reciverName,SenderUID;
//    CircleImageView profile;
//    TextView reciverNName;
//    FirebaseDatabase database;
//    Uri imageUri;
//    FirebaseAuth firebaseAuth;
//    public  static String senderImg;
//    public  static String reciverIImg;
//    CardView sendbtn;
//    CardView imagebtn;
//    EditText textmsg;
//    private static final int PICK_IMAGE_REQUEST = 1;
//    String senderRoom,reciverRoom;
//    RecyclerView messageAdpter;
//    ArrayList<msgModelclass> messagesArrayList;
//    messagesAdpter mmessagesAdpter;
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chatwindo);
//        database = FirebaseDatabase.getInstance();
//        firebaseAuth = FirebaseAuth.getInstance();
//
//        reciverName = getIntent().getStringExtra("nameeee");
//        reciverimg = getIntent().getStringExtra("reciverImg");
//        reciverUid = getIntent().getStringExtra("uid");
//
//        messagesArrayList = new ArrayList<>();
//imagebtn=findViewById(R.id.camera);
//        sendbtn = findViewById(R.id.sendbtnn);
//        textmsg = findViewById(R.id.textmsg);
//        reciverNName = findViewById(R.id.recivername);
//        profile = findViewById(R.id.profileimgg);
//        messageAdpter = findViewById(R.id.msgadpter);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setStackFromEnd(true);
//        messageAdpter.setLayoutManager(linearLayoutManager);
//        mmessagesAdpter = new messagesAdpter(chatwindo.this,messagesArrayList,senderRoom,reciverRoom);
//        messageAdpter.setAdapter(mmessagesAdpter);
//
//
//        Picasso.get().load(reciverimg).into(profile);
//        reciverNName.setText(reciverName);
//
//        SenderUID =  firebaseAuth.getUid();
//
//        senderRoom = SenderUID+reciverUid;
//        reciverRoom = reciverUid+SenderUID;
//
//
//
//
//
//        DatabaseReference reference = database.getReference().child("user").child(firebaseAuth.getUid());
//        DatabaseReference  chatreference = database.getReference().child("chats").child(senderRoom).child("messages");
//
//
//        chatreference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                messagesArrayList.clear();
//                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
//                    msgModelclass messages = dataSnapshot.getValue(msgModelclass.class);
//                    messagesArrayList.add(messages);
//                }
//                mmessagesAdpter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                senderImg= snapshot.child("profilepic").getValue().toString();
//                reciverIImg=reciverimg;
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        sendbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String message = textmsg.getText().toString();
//                if (message.isEmpty()){
//                    Toast.makeText(chatwindo.this, "Enter The Message First", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                textmsg.setText("");
//                Date date = new Date();
//                msgModelclass messagess = new msgModelclass(message,SenderUID,date.getTime());
//
//                database=FirebaseDatabase.getInstance();
//                database.getReference().child("chats")
//                        .child(senderRoom)
//                        .child("messages")
//                        .push().setValue(messagess).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                database.getReference().child("chats")
//                                        .child(reciverRoom)
//                                        .child("messages")
//                                        .push().setValue(messagess).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                            @Override
//                                            public void onComplete(@NonNull Task<Void> task) {
//
//                                            }
//                                        });
//                            }
//                        });
//            }
//        });
//
//
//    }
//
//
//}


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatapp.R;
import com.example.chatapp.messagesAdpter;
import com.example.chatapp.msgModelclass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class chatwindo extends AppCompatActivity {

    String reciverimg, reciverUid, reciverName, SenderUID;
    CircleImageView profile;
    TextView reciverNName;
    FirebaseDatabase database;
    Uri imageUri;
    FirebaseAuth firebaseAuth;
    public static String senderImg;
    public static String reciverIImg;
    CardView sendbtn, imagebtn;
    EditText textmsg;
    private static final int PICK_IMAGE_REQUEST = 1;
    String senderRoom, reciverRoom;
    RecyclerView messageAdpter;
    ArrayList<msgModelclass> messagesArrayList;
    messagesAdpter mmessagesAdpter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatwindo);
        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        reciverName = getIntent().getStringExtra("nameeee");
        reciverimg = getIntent().getStringExtra("reciverImg");
        reciverUid = getIntent().getStringExtra("uid");

        messagesArrayList = new ArrayList<>();
        imagebtn = findViewById(R.id.camera);
        sendbtn = findViewById(R.id.sendbtnn);
        textmsg = findViewById(R.id.textmsg);
        reciverNName = findViewById(R.id.recivername);
        profile = findViewById(R.id.profileimgg);
        messageAdpter = findViewById(R.id.msgadpter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        messageAdpter.setLayoutManager(linearLayoutManager);
        mmessagesAdpter = new messagesAdpter(chatwindo.this, messagesArrayList, senderRoom, reciverRoom);
        messageAdpter.setAdapter(mmessagesAdpter);

        Picasso.get().load(reciverimg).into(profile);
        reciverNName.setText(reciverName);

        SenderUID = firebaseAuth.getUid();

        senderRoom = SenderUID + reciverUid;
        reciverRoom = reciverUid + SenderUID;

        DatabaseReference reference = database.getReference().child("user").child(firebaseAuth.getUid());
        DatabaseReference chatreference = database.getReference().child("chats").child(senderRoom).child("messages");

        chatreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messagesArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    msgModelclass messages = dataSnapshot.getValue(msgModelclass.class);
                    messagesArrayList.add(messages);
                }
                mmessagesAdpter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = textmsg.getText().toString();
                if (message.isEmpty()) {
                    Toast.makeText(chatwindo.this, "Please enter a valid message", Toast.LENGTH_SHORT).show();
                    return;
                }
                textmsg.setText("");
                Date date = new Date();
                msgModelclass messages = new msgModelclass(message, SenderUID, date.getTime(), false);
                database.getReference().child("chats").child(senderRoom).child("messages").push().setValue(messages).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        database.getReference().child("chats").child(reciverRoom).child("messages").push().setValue(messages);
                    }
                });
            }
        });

        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            uploadImage();
        }
    }

    private void uploadImage() {
        if (imageUri != null) {
            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images/" + System.currentTimeMillis() + ".jpg");
            storageReference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    Uri downloadUri = task.getResult();
                                    sendMessageWithImage(downloadUri.toString());
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    private void sendMessageWithImage(String imageUrl) {
        Date date = new Date();
        msgModelclass messages = new msgModelclass(imageUrl, SenderUID, date.getTime(), true);
        database.getReference().child("chats").child(senderRoom).child("messages").push().setValue(messages).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                database.getReference().child("chats").child(reciverRoom).child("messages").push().setValue(messages);
            }
        });
    }
}
