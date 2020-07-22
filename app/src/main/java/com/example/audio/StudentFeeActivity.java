package com.example.audio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

public class StudentFeeActivity extends AppCompatActivity {
EditText ed;
ListView listView;
ImageButton addFeebtn;
FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_fee);

        ed=findViewById(R.id.text);
        ed.setKeyListener(null);
        listView=findViewById(R.id.feeList);
        addFeebtn=findViewById(R.id.feeAddBtn);
        final String docId=getIntent().getExtras().getString("docId").toString();
        ed.setText(docId);

        firebaseFirestore= FirebaseFirestore.getInstance();



        final FeeListAdapter feeListAdapter = new FeeListAdapter(this, R.layout.fee_list_item);



        //messages are being shown in the following list view

        CollectionReference collectionReference=firebaseFirestore.collection("Details").document(docId)
                .collection("fee");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    firebaseFirestore.collection("Details").document(docId)
                            .collection("fee").orderBy("time", Query.Direction.ASCENDING)
                            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                                    feeListAdapter.clear();

                                    feeListAdapter.addAll(queryDocumentSnapshots.toObjects(FeePojo.class));
                                    listView.setAdapter(feeListAdapter);
                                    int totalMessage = queryDocumentSnapshots.size();

                                    listView.smoothScrollToPosition(1);
                                    listView.setSelection(0);


                                    Toast.makeText(StudentFeeActivity.this, "Data Fetched", Toast.LENGTH_SHORT).show();
                                }
                            });




                }
            }
        });




        addFeebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentFeeActivity.this,AddFeeActivity.class);
                intent.putExtra("docId",docId);
                startActivity(intent);

            }
        });

    }
}
