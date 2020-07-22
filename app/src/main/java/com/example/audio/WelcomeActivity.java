package com.example.audio;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
int time_out=2000;

ImageView img;
TextView textView;
LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        img=findViewById(R.id.splash_img);
        textView=findViewById(R.id.splash_txt);
        linearLayout=findViewById(R.id.anim_layout);
        ObjectAnimator animation1 = ObjectAnimator.ofFloat(linearLayout , "translationY", -300f);
        animation1.setDuration(2000);
        animation1.start();

        Animation animation= AnimationUtils.loadAnimation(this,R.anim.mytransition);
        linearLayout.startAnimation(animation);

        Thread timer=new Thread(){
            public void run() {
                try {
                    sleep(4000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();


        //simple splash screen which stops creen for time out value

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        },time_out);


    }
}
