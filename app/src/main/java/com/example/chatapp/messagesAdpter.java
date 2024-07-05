//package com.example.chatapp;
//
//import static com.example.chatapp.chatwindo.reciverIImg;
//import static com.example.chatapp.chatwindo.senderImg;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
//public class messagesAdpter extends RecyclerView.Adapter {
//    Context context;
//    ArrayList<msgModelclass> messagesAdpterArrayList;
//    int ITEM_SEND = 1;
//    int ITEM_RECIVE = 2;
//    String senderRoom,reciverRoom;
//
//    public messagesAdpter(Context context, ArrayList<msgModelclass> messagesAdpterArrayList, String senderRoom, String reciverRoom) {
//        this.context = context;
//        this.messagesAdpterArrayList = messagesAdpterArrayList;
//        this.senderRoom = senderRoom;
//        this.reciverRoom = reciverRoom;
//    }
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        if (viewType == ITEM_SEND) {
//            View view = LayoutInflater.from(context).inflate(R.layout.sender_layout, parent, false);
//            return new senderVierwHolder(view);
//        } else {
//            View view = LayoutInflater.from(context).inflate(R.layout.reciver_layout, parent, false);
//            return new reciverViewHolder(view);
//        }
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        msgModelclass messages = messagesAdpterArrayList.get(position);
//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//
//
//            @Override
//            public boolean onLongClick(View view) {
//                new AlertDialog.Builder(context).setTitle("Delete")
//                        .setMessage("Are you sure you want to delete this message?")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                            }
//                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                dialogInterface.dismiss();
//                            }
//                        }).show();
//
//                return false;
//            }
//        });
//        if (holder.getClass() == senderVierwHolder.class) {
//            senderVierwHolder viewHolder = (senderVierwHolder) holder;
//            viewHolder.msgtxt.setText(messages.getMessage());
//            Picasso.get().load(senderImg).into(viewHolder.circleImageView);
//        } else {
//            reciverViewHolder viewHolder = (reciverViewHolder) holder;
//            viewHolder.msgtxt.setText(messages.getMessage());
//            Picasso.get().load(reciverIImg).into(viewHolder.circleImageView);
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return messagesAdpterArrayList.size();
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        msgModelclass messages = messagesAdpterArrayList.get(position);
//        if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(messages.getSenderid())) {
//            return ITEM_SEND;
//        } else {
//            return ITEM_RECIVE;
//        }
//    }
//
//    class senderVierwHolder extends RecyclerView.ViewHolder {
//        CircleImageView circleImageView;
//        TextView msgtxt;
//
//        public senderVierwHolder(@NonNull View itemView) {
//            super(itemView);
//            circleImageView = itemView.findViewById(R.id.profilerggg);
//            msgtxt = itemView.findViewById(R.id.msgsendertyp);
//        }
//    }
//
//    class reciverViewHolder extends RecyclerView.ViewHolder {
//        CircleImageView circleImageView;
//        TextView msgtxt;
//
//        public reciverViewHolder(@NonNull View itemView) {
//            super(itemView);
//            circleImageView = itemView.findViewById(R.id.pro);
//            msgtxt = itemView.findViewById(R.id.recivertextset);
//        }
//    }
//}


package com.example.chatapp;

import static com.example.chatapp.chatwindo.reciverIImg;
import static com.example.chatapp.chatwindo.senderImg;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class messagesAdpter extends RecyclerView.Adapter {

    Context context;
    ArrayList<msgModelclass> messagesAdpterArrayList;
    int ITEM_SEND = 1;
    int ITEM_RECIVE = 2;
    int ITEM_SEND_IMAGE = 3;
    int ITEM_RECIVE_IMAGE = 4;
    String senderRoom, reciverRoom;

    public messagesAdpter(Context context, ArrayList<msgModelclass> messagesAdpterArrayList, String senderRoom, String reciverRoom) {
        this.context = context;
        this.messagesAdpterArrayList = messagesAdpterArrayList;
        this.senderRoom = senderRoom;
        this.reciverRoom = reciverRoom;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_SEND) {
            View view = LayoutInflater.from(context).inflate(R.layout.sender_layout, parent, false);
            return new senderViewHolder(view);
        } else if (viewType == ITEM_RECIVE) {
            View view = LayoutInflater.from(context).inflate(R.layout.reciver_layout, parent, false);
            return new reciverViewHolder(view);
        } else if (viewType == ITEM_SEND_IMAGE) {
            View view = LayoutInflater.from(context).inflate(R.layout.send_image ,parent, false);
            return new senderImageViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.receive_image, parent, false);
            return new reciverImageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        msgModelclass messages = messagesAdpterArrayList.get(position);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(context).setTitle("Delete")
                        .setMessage("Are you sure you want to delete this message?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Handle deletion here
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).show();

                return false;
            }
        });
        if (holder.getClass() == senderViewHolder.class) {
            senderViewHolder viewHolder = (senderViewHolder) holder;
            viewHolder.msgtxt.setText(messages.getMessage());
            Picasso.get().load(senderImg).into(viewHolder.circleImageView);
        } else if (holder.getClass() == reciverViewHolder.class) {
            reciverViewHolder viewHolder = (reciverViewHolder) holder;
            viewHolder.msgtxt.setText(messages.getMessage());
            Picasso.get().load(reciverIImg).into(viewHolder.circleImageView);
        } else if (holder.getClass() == senderImageViewHolder.class) {
            senderImageViewHolder viewHolder = (senderImageViewHolder) holder;
            Picasso.get().load(messages.getMessage()).into(viewHolder.imageView);
        } else {
            reciverImageViewHolder viewHolder = (reciverImageViewHolder) holder;
            Picasso.get().load(messages.getMessage()).into(viewHolder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return messagesAdpterArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        msgModelclass messages = messagesAdpterArrayList.get(position);
        if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(messages.getSenderid())) {
            if (messages.isImage()) {
                return ITEM_SEND_IMAGE;
            } else {
                return ITEM_SEND;
            }
        } else {
            if (messages.isImage()) {
                return ITEM_RECIVE_IMAGE;
            } else {
                return ITEM_RECIVE;
            }
        }
    }

    class senderViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView msgtxt;

        public senderViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.profilerggg);
            msgtxt = itemView.findViewById(R.id.msgsendertyp);
        }
    }

    class reciverViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView msgtxt;

        public reciverViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.pro);
            msgtxt = itemView.findViewById(R.id.recivertextset);
        }
    }

    class senderImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public senderImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.sender_image);
        }
    }

    class reciverImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public reciverImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.receiver_image);
        }
    }
}
