package com.example.audio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SubscriptionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.net.URLEncoder;

public class SendSmsActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    EditText mssg, numbers;
    Button sendBtn, sim1, sim2;
    int simNo = 0;
    FirebaseFirestore firebaseFirestore;
    Array numberArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        firebaseFirestore=FirebaseFirestore.getInstance();

        mssg = findViewById(R.id.smsM);
        sendBtn = findViewById(R.id.sendMsgBtn);
        numbers = findViewById(R.id.smsNum);
        sim1 = findViewById(R.id.sendMsgBtnsim1);
        sim2 = findViewById(R.id.sendMsgBtnsim2);

        sendBtn.setVisibility(View.GONE);



        


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // For Internet
                SendThrughInternet();
            }
        });//click listener


        //From phone
        sim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simNo = 1;
                SendByPhone();

            }
        });


        sim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simNo = 2;
                SendByPhone();

            }
        });


    }


    public void SendThrughInternet() {
        try {
            String apiKey = "TNYJ5MPaSgGHcBkpbeFCZ8mL3z6lx2oiIXVy7Af4wuDU1qdQ0E6sGVhNYWDz9J8rKgPfwLHXA7Mc0SuT";
            String sendId = "FSTSMS";
            String message = mssg.getText().toString().trim();
            //important step...
            message = URLEncoder.encode(message, "UTF-8");
            String language = "english";
            String number = numbers.getText().toString();
            String route = "p";
            String url = "https://www.fast2sms.com/dev/bulk?authorization=" + apiKey + "&sender_id=" + sendId + "&message=" + message + "&language=" + language + "&route=" + route + "&numbers=" + number;
            RequestQueue queue = Volley.newRequestQueue(SendSmsActivity.this);
// Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            Toast.makeText(SendSmsActivity.this, response, Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(SendSmsActivity.this, "error ", Toast.LENGTH_SHORT).show();
                }
            });
// Add the request to the RequestQueue.
            queue.add(stringRequest);
        } catch (Exception e) {
            // TODO: handle exception
            Toast.makeText(SendSmsActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }


    public void SendByPhone() {

        if (ContextCompat.checkSelfPermission(SendSmsActivity.this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }

        } else {
            SendMethod();

        }


    }

    public void SendMethod() {
        String message = mssg.getText().toString().trim();
        String number = numbers.getText().toString();

        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                SubscriptionManager subs = (SubscriptionManager) getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
                if (subs != null) {
                    Log.d("sim_spalsh", "num sims = " + subs.getActiveSubscriptionInfoCountMax());

                    if (subs.getActiveSubscriptionInfoCountMax() > 1) {

                        //SendSMS From SIM One
                        if (simNo == 1) {
                            SmsManager.getSmsManagerForSubscriptionId(0)
                                    .sendTextMessage(number, null, message, null, null);
                            Toast.makeText(SendSmsActivity.this, "SMS Sent Successfully by sim1", Toast.LENGTH_SHORT).show();
                            simNo = 0;
                        }


                        //SendSMS From SIM Two
                        if (simNo == 2) {
                            SmsManager.getSmsManagerForSubscriptionId(1)
                                    .sendTextMessage(number, null, "sim2", null, null);
                            Toast.makeText(SendSmsActivity.this, "SMS Sent Successfully by sim2", Toast.LENGTH_SHORT).show();
                            simNo = 0;
                        }
                    } else {
                        SmsManager smgr = SmsManager.getDefault();
                        smgr.sendTextMessage(number, null, message, null, null);
                    }
                }
            }

            mssg.setText("");
            numbers.setText("");
        } catch (Exception e) {
            Toast.makeText(SendSmsActivity.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
        }
    }

}
