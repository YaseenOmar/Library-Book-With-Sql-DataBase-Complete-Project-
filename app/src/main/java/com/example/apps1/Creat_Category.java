package com.example.apps1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

public class Creat_Category extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button create ;
    EditText nameCategory ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_categoty);
        create = findViewById(R.id.createCategoryBtn);
        nameCategory = findViewById(R.id.nameCategory);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDataBase db = new MyDataBase(Creat_Category.this);
                String name = nameCategory.getText().toString();

               Category category = new Category(name);
               db.insertCategory(category);

               nameCategory.getText().clear();
            }

        });



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}