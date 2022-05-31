package com.example.apps1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Library_Books extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_library);
        Button But1=findViewById(R.id.But1);
        Button But2=findViewById(R.id.But2);
        Button But3=findViewById(R.id.But3);
        Button But4=findViewById(R.id.But4);

        But1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btu1=new Intent(getApplicationContext(),Favorites.class);
                startActivity(btu1);
            }
        });
        But2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btu2=new Intent(getApplicationContext(),Libary.class);
                startActivity(btu2);
            }
        });
        But3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btu3=new Intent(getApplicationContext(), Creat_Category.class);
                startActivity(btu3);
            }
        });
        But4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btu4=new Intent(getApplicationContext(),Create_Book.class);
                startActivity(btu4);
            }
        });


    }
}