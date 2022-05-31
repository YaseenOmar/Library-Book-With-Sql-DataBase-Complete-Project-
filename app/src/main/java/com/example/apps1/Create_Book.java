package com.example.apps1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;


public class Create_Book extends AppCompatActivity  {
    int selected_val;
    static byte[] imageContent;
    Button createBook ;
    EditText nameBook , authorName , relaseYear , pagesNum ;
    ImageView image0 ;
    ImageView image ;
    Spinner spinCategory ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_book);

        image0 = findViewById(R.id.btn_img);
        image = findViewById(R.id.v_img);
        createBook = findViewById(R.id.creatBookBtn);
        nameBook = findViewById(R.id.BookName);
        authorName = findViewById(R.id.AuthorName);
        relaseYear = findViewById(R.id.RelaseYear);
        pagesNum = findViewById(R.id.PagesNum);
        spinCategory = findViewById(R.id.spinCategory);

       MyDataBase db = new MyDataBase(this);
       ArrayList<Category> allCategory = db.getAllCategory();
       ArrayList<String> allCat = new ArrayList<>();
       String cat;
        for (int i = 0 ; i<allCategory.size(); i++){
            cat = allCategory.get(i).getNameCategory();
            allCat.add(cat);
        }


        ArrayAdapter arrayAdapter = new ArrayAdapter(this , android.R.layout.simple_spinner_item
                ,allCat);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinCategory.setAdapter(arrayAdapter);


        //******************************




        //******************************

        createBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDataBase db = new MyDataBase(Create_Book.this);

                String name = nameBook.getText().toString();
                String author = authorName.getText().toString();
                String year = relaseYear.getText().toString();
                String pagenum =pagesNum.getText().toString();
                String catBook = spinCategory.getSelectedItem().toString();
                Book book = new Book(name , author , year , pagenum , catBook );

                book.setImage(imageContent);
                db.insertBook(book );

                nameBook.getText().clear();
                authorName.getText().clear();
                relaseYear.getText().clear();
                pagesNum.getText().clear();

            }
        });

//***********************
        //Image Picker ***
        //***********
        image0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean pick = true;
                if (pick == true){
                    if (!checkCameraPermision()){
                        requestCameraPermision();
                    }else pickImage();
                }else {
                    if (!checkStoragePermision()){
                        requestStoragePermision();
                    }else pickImage();

                }
            }
        });



    }

    private void pickImage() {
        CropImage.activity().start(this);
    }

    private void requestStoragePermision() {
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE} , 100);


    }

    private void requestCameraPermision() {

        requestPermissions(new String[]{Manifest.permission.CAMERA , Manifest.permission.WRITE_EXTERNAL_STORAGE} , 100);
    }

    private boolean checkStoragePermision() {
        boolean res2 = ContextCompat.checkSelfPermission(this , Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED;
        return res2 ;
    }

    private boolean checkCameraPermision() {
        boolean res1 = ContextCompat.checkSelfPermission(this , Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED;
        boolean res2 = ContextCompat.checkSelfPermission(this , Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED;
    return  res1 && res2;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                try {
                    InputStream stream = getContentResolver().openInputStream(resultUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(stream);
                    image.setImageBitmap(bitmap);
                    imageContent = getBytes(bitmap);
                }catch (Exception e){
                    e.printStackTrace();
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    public byte[] getBytes(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0 , stream);

        return stream.toByteArray();
    }


}





