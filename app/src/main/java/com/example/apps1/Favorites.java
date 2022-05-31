package com.example.apps1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;

import java.util.ArrayList;

public class Favorites extends AppCompatActivity {
    RecyclerView rv ;
    ArrayList<Book> listBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
//        MyDataBase db = new MyDataBase(this);
//        rv= findViewById(R.id.fav);
//        listBook = db.getBookFav();
//
//        BookAdapter adapter = new BookAdapter(listBook , this);
//        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
//        rv.setHasFixedSize(true);
//        rv.setLayoutManager(lm);
//        rv.setAdapter(adapter);
//        adapter.setClickListener(this);


    }
}