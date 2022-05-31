package com.example.apps1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Book_List extends AppCompatActivity implements ItemClickListener{
    RecyclerView rv ;
    String catName ;
    ImageButton deleteIcon;
    ArrayList<Book> listBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lbrary_book);
        rv= findViewById(R.id.library_Book);
        deleteIcon = findViewById(R.id.deleteBTN);
        catName = getIntent().getStringExtra("CategoryName");

        MyDataBase db = new MyDataBase(this);

       listBook = db.getBook(catName);


        BookAdapter adapter = new BookAdapter(listBook , this);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
        adapter.setClickListener(this);



    }


    @Override
    public void onClick(View view, int position) {
        int id = listBook.get(position).getID_Book();
        Intent i = new Intent(this , Ubdate_Book.class);
        i.putExtra("ID", id);
        i.putExtra("CategoryName" , catName);
//        Toast.makeText(this, ""+listBook.get(position).getID_Book(), Toast.LENGTH_SHORT).show();
        startActivity(i);
    }
}