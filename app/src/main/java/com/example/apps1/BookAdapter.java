package com.example.apps1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    ArrayList<Book> books ;
    Context context ;
    boolean x = true;
    private ItemClickListener clickListener;
    public BookAdapter(ArrayList<Book> books , Context context) {
        this.books = books;
        this.context = context;

    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_book , null , false);
        BookViewHolder viewHolder = new BookViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        int p = position;
        Book b = books.get(position);
        holder.tvNameBook.setText(b.getNameBook());
        holder.tvNameAuthor.setText(b.getAuthorName());
        holder.pages.setText(b.getPagesNum());
        b.setFaverot(!x);

        holder.favbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                Toast.makeText(context, "ddsdsd"+b.isFaverot(), Toast.LENGTH_SHORT).show();
                if(x) {

                    holder.favbtn.setImageDrawable(context.getResources().getDrawable(R.drawable.fav));
                    x=!x;
                }else {
                    holder.favbtn.setImageDrawable(context.getResources().getDrawable(R.drawable.fivert));

                    x=!x;
                }
            }
        });
        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMethod(p);

            }
        });
//        notifyItemRangeChanged(position, getItemCount());

        Bitmap imagContent = BitmapFactory.decodeByteArray(b.getImage() , 0 , b.getImage().length);
        holder.ivImageBook.setImageBitmap(imagContent);

    }

    @Override
    public int getItemCount() {
        return books.size();
    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    //**********
    class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvNameBook , tvNameAuthor , pages ;
        ImageView ivImageBook;
        ImageButton deleteIcon , favbtn;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameBook = itemView.findViewById(R.id.nameBookList);
            tvNameAuthor = itemView.findViewById(R.id.nameAuthorList);
            pages = itemView.findViewById(R.id.PagesNumList);
            ivImageBook = itemView.findViewById(R.id.imageBook);
            deleteIcon = itemView.findViewById(R.id.deleteBTN);
            favbtn = itemView.findViewById(R.id.favBTN);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());

        }
    }
    public void deleteMethod(int position) {
        Book b = books.get(position);
        int id =b.getID_Book();
        MyDataBase db = new MyDataBase(context);
        db.deleteBook(id);
        books.remove(position);

        notifyItemRemoved(position);
        //enables smooth animations and also updates data
        notifyItemRangeChanged(position, getItemCount());


    }
}
