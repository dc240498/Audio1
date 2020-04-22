package com.example.audio;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.annotation.Nullable;

public class ExistingUserActivity extends AppCompatActivity {

    ListView listView;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_user);

        listView=findViewById(R.id.existing_list);
        firebaseFirestore=FirebaseFirestore.getInstance();



        final ChatAdapter chatAdapter = new ChatAdapter(this, R.layout.item_chat);



        //messages are being shown in the following list view


        firebaseFirestore.collection("Details").orderBy("name", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                        chatAdapter.clear();

                        chatAdapter.addAll(queryDocumentSnapshots.toObjects(Chat.class));
                        listView.setAdapter(chatAdapter);
                        int totalMessage = queryDocumentSnapshots.size();

                        listView.smoothScrollToPosition(totalMessage-1);
                        listView.setSelection(chatAdapter.getCount()-1);

                        Toast.makeText(ExistingUserActivity.this, "Data Fetched", Toast.LENGTH_SHORT).show();
                    }
                });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                 Chat obj=chatAdapter.getItem(position);
                Intent intent=new Intent(ExistingUserActivity.this,StudentFeeActivity.class);
                String name=obj.name;
                String num=obj.mobile;
                String docId=name.concat(num);
                intent.putExtra("docId",docId);
                startActivity(intent);

            }
        });





    }
}
