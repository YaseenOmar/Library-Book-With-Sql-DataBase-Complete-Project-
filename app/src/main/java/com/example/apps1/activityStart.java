package com.example.apps1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class activityStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
       ImageView imag=findViewById(R.id.imag);
        TextView text=findViewById(R.id.texta);

       Thread thred=new Thread(){
           @Override
           public void run() {
               try {
                  sleep(3000);

                   Intent intt=new Intent(getApplicationContext(), Library_Books.class);
                   startActivity(intt);
                   finish();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }   ;
       thred.start();
        Animation anim= AnimationUtils.loadAnimation(this,R.anim.trnslat);
        imag.setAnimation(anim);
       // Animation t= AnimationUtils.loadAnimation(this,R.anim.txtf);
       // text.setAnimation(t);
        Animation c= AnimationUtils.loadAnimation(this,R.anim.trns);
        text.setAnimation(c);




    }
}