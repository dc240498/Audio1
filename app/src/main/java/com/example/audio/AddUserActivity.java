package com.example.audio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddUserActivity extends AppCompatActivity {

    EditText name,mobile,father,batch;
    Button btn;
    FirebaseFirestore firebaseFirestore;
    Map<String,String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        name=findViewById(R.id.add_name);
        mobile=findViewById(R.id.add_Mobile);
        father=findViewById(R.id.add_Father);
        batch=findViewById(R.id.add_Batch);
        btn=findViewById(R.id.add_submit);

        firebaseFirestore=FirebaseFirestore.getInstance();
        map=new HashMap<>();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("name",name.getText().toString().trim());
                map.put("mobile",mobile.getText().toString().trim());
                map.put("father",father.getText().toString().trim());
                map.put("batch",batch.getText().toString().trim());
                String docId=name.getText().toString().trim().concat(mobile.getText().toString().trim());

                firebaseFirestore.collection("Details").document(docId)
                        .set(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Toast.makeText(AddUserActivity.this,"Data Uploded",Toast.LENGTH_SHORT).show();
                                name.setText("");
                                father.setText("");
                                mobile.setText("");
                                batch.setText("");

                            }
                        });
            }
        });





    }
}
