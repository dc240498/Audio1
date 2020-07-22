package com.example.audio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddFeeActivity extends AppCompatActivity {

    EditText feeAmount,feeRemark;
    Button sbmtbtn;
    FirebaseFirestore firebaseFirestore;
    Map<String,String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fee);

        final String docId= getIntent().getExtras().getString("docId");
        firebaseFirestore=FirebaseFirestore.getInstance();
        map=new HashMap<>();

        feeAmount=findViewById(R.id.add_fee_mny);
        feeRemark=findViewById(R.id.add_fee_remark);
        sbmtbtn=findViewById(R.id.add_fee_btn);



        sbmtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String remark="";
                if (feeRemark.getText().toString().trim()=="") {
                remark="No Remark";
                }else {
                remark=feeRemark.getText().toString().trim();
                }
                String time=String.valueOf(Calendar.getInstance().getTime());
                map.put("fee",feeAmount.getText().toString().trim());
                map.put("time",time );
                map.put("remark",remark);

                firebaseFirestore.collection("Details").document(docId)
                        .collection("fee").document()
                        .set(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Toast.makeText(AddFeeActivity.this,"Data Uploded",Toast.LENGTH_SHORT).show();
                                feeAmount.setText("");
                                feeRemark.setText("");


                            }
                        });

            }
        });







    }
}
