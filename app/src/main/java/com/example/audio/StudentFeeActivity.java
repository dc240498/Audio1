package com.example.audio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class StudentFeeActivity extends AppCompatActivity {
EditText ed;
ListView feeList;
Button addFeebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_fee);

        ed=findViewById(R.id.text);
        String docId=getIntent().getExtras().getString("docId").toString();
        ed.setText(docId);


    }
}
