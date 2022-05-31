package com.example.apps1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Libary extends AppCompatActivity implements ItemClickListener {
    RecyclerView rv ;
    ArrayList<Category> listCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libary);
        rv= findViewById(R.id.library);

        MyDataBase db = new MyDataBase(this);
         listCategory = db.getAllCategory();
    CategoryAdabter adabter = new CategoryAdabter(listCategory , this);
    RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
    rv.setHasFixedSize(true);
    rv.setLayoutManager(lm);
    rv.setAdapter(adabter);
    adabter.setClickListener(this);
 }


    @Override
    public void onClick(View view, int position) {
//istCategory.get(position).getNameCategory()
//        Toast.makeText(this, ""+listCategory.get(position).getNameCategory(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, Book_List.class);
        String x = listCategory.get(position).getNameCategory();
        i.putExtra("CategoryName", x);

        startActivity(i);


    }
}