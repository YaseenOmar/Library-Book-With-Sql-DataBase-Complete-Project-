package com.example.apps1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.security.PublicKey;
import java.util.ArrayList;

public class MyDataBase extends SQLiteOpenHelper {
    static String tableNameBook = "BOOKS";
    static String tableNameCategory= "CATEGORYS";
    private  static String db_name = "Library.db";
    private static int db_version = 1;
    Context context;
    public MyDataBase(@Nullable Context context) {
        super(context, db_name, null, db_version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryCategory = "CREATE TABLE "+tableNameCategory+" (" +
                "NAME_CATEGORY TEXT PRIMARY KEY NOT NULL);";
        sqLiteDatabase.execSQL(queryCategory);

        String queryBook = "CREATE TABLE " +tableNameBook+"(ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME_BOOK TEXT , " +
                "AUTHOR_NAME TEXT , RELASE_YEAR TEXT , PAGES_NUM INTEGER, IMAGE BLOB , " +
                " NAME_CATEGORY TEXT , FOREIGN KEY(NAME_CATEGORY) REFERENCES  CATEGORYS(NAME_CATEGORY)" +
                ") ;";
        sqLiteDatabase.execSQL(queryBook);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String queryCategory = "DROP TABLE IF EXISTS "+tableNameCategory+" ;";
        sqLiteDatabase.execSQL(queryCategory);
        String queryBook = "DROP TABLE IF EXISTS "+tableNameBook+" ;";
        sqLiteDatabase.execSQL(queryBook);
        onCreate(sqLiteDatabase);
    }
    public void insertBook (Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME_BOOK" , book.getNameBook());
        cv.put("AUTHOR_NAME",book.getAuthorName());
        cv.put("RELASE_YEAR", book.getRelaseYear());
        cv.put("PAGES_NUM" , book.getPagesNum());
        cv.put("IMAGE" , book.getImage());
        cv.put("NAME_CATEGORY" , book.getCatBook());
        long result = db.insert(tableNameBook , null , cv);

        if (result!=-1){
            Toast.makeText(context , "Added Succefully ",Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(context , "Added Failed ",Toast.LENGTH_SHORT).show();
    }
    public void insertCategory(Category category){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME_CATEGORY" , category.getNameCategory());
        long result = db.insert(tableNameCategory , null , cv);
        if (result!=-1){
            Toast.makeText(context , "Added Succefully ",Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(context , "Added Failed ",Toast.LENGTH_SHORT).show();
    }
    public ArrayList<Category> getAllCategory (){

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT NAME_CATEGORY FROM "+tableNameCategory+" ;";
        Cursor cursor = null ;
        if (db!=null){

            cursor =  db.rawQuery(query , null );
        }

        if (cursor==null){
            return null;
        }else{

            ArrayList<Category> category = new ArrayList<>();
            while (cursor.moveToNext()){
                String nameCategory = cursor.getString(0);

                Category c = new Category(nameCategory);

                category.add(c);

            }
            return category;
        }
    }



    public ArrayList<Book> getBook (String Name) {


        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Book> book;

        try (Cursor cursor = db.rawQuery("select * from " + tableNameBook + " where NAME_CATEGORY =?", new String[]{Name})) {
            //(Cursor cursor = db.rawQuery("Select NAME_BOOK from BOOKS where NAME_CATEGORY=? " +Name ,null)) {

            book = new ArrayList<>();


            if (cursor.moveToFirst()) {
                do {
                    int idBook = cursor.getInt(0);
                    String nameBook = cursor.getString(1);

                String nameAuthor = cursor.getString(2);

                String relaseYear = cursor.getString(3);
                String pagesNum = cursor.getString(4);
                    byte [] imageContent = cursor.getBlob(5);

                String cat = cursor.getString(6);

                Book b = new Book(nameBook, nameAuthor, relaseYear, pagesNum, cat );

                    b.setImage(imageContent);
                    b.setID_Book(idBook);
                book.add(b);

                } while (cursor.moveToNext());
            }
            return book;


        }


    }

    public void updateBook (Book book , int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME_BOOK" , book.getNameBook());
        cv.put("AUTHOR_NAME",book.getAuthorName());
        cv.put("RELASE_YEAR", book.getRelaseYear());
        cv.put("PAGES_NUM" , book.getPagesNum());
        cv.put("IMAGE" , book.getImage());
       // cv.put("NAME_CATEGORY" , book.getCatBook());
//        db.update(tableNameBook, cv, "_id = ?", new String[]{nameCat});
            String id0 = String.valueOf(id);
        long result =    db.update(tableNameBook, cv, "ID = ?", new String[]{id0});

        if (result!=-1){
            Toast.makeText(context , "Updated Succefully ",Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(context , "Updated Failed ",Toast.LENGTH_SHORT).show();
    }

        public boolean deleteBook(int id)
        {
            SQLiteDatabase db = this.getWritableDatabase();
            String id0 = String.valueOf(id);

            return db.delete(tableNameBook,   "ID =?", new String[]{id0}) > 0;
        }


    public boolean deleteCat(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //String id0 = String.valueOf(id);
        db.delete(tableNameBook,   "NAME_CATEGORY =?", new String[]{name});
        return db.delete(tableNameCategory,   "NAME_CATEGORY =?", new String[]{name}) > 0;
    }







}

