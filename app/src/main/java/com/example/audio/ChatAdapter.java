package com.example.audio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;

public class ChatAdapter extends ArrayAdapter<Chat> {


    Context context;
    int resource;

    public ChatAdapter( @NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null)
           convertView = LayoutInflater.from(context).inflate(resource,parent,false);

        Chat chat = getItem(position);

        TextView feedUser = convertView.findViewById(R.id.chat_feed_user);
        TextView timeUser = convertView.findViewById(R.id.chat_time_user);
        TextView fatherUser = convertView.findViewById(R.id.chat_feed_userF);
        TextView numberUser = convertView.findViewById(R.id.chat_feed_userN);


        //TextView feedOther = convertView.findViewById(R.id.chat_feed_other);
        //TextView timeOther = convertView.findViewById(R.id.chat_time_other);

        feedUser.setText(chat.getName());
        timeUser.setText(chat.getBatch());
        fatherUser.setText(chat.getFather());
        numberUser.setText(chat.getMobile());

       // feedOther.setText(chat.getName());
       // timeOther.setText(chat.getBatch());


        return convertView;
    }
}
