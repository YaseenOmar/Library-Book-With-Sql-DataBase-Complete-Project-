package com.example.apps1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryAdabter extends RecyclerView.Adapter<CategoryAdabter.CategoryViewHolder> {
    ArrayList<Category> categories ;
    Context context ;
    private ItemClickListener clickListener;

    public CategoryAdabter(ArrayList<Category> categories ,Context context) {
        this.categories = categories;
        this.context = context;
    }




    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType ) {

       View v= LayoutInflater.from(context).inflate(R.layout.list_category , null , false);

        CategoryViewHolder viewHolder =  new CategoryViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
int p =position;
        Category c = categories.get(position);
        holder.tvNameCategory.setText(c.getNameCategory());
        holder.deletecategorey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMethod(p);

            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    //********************
    class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvNameCategory ;
        ImageButton deletecategorey;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameCategory = itemView.findViewById(R.id.tvnameCategory);
            deletecategorey=itemView.findViewById(R.id.deleteCat);
            itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View view) {
//        int position = getAdapterPosition();
//            Intent i = new Intent(context, Book_List.class);
//           context.startActivity(i);
//            Toast.makeText(context, "position : "+(position+1), Toast.LENGTH_SHORT).show();
//        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());

        }
    }
    public void deleteMethod(int position) {

        Category c = categories.get(position);
        String NameCate =c.getNameCategory();
        MyDataBase db = new MyDataBase(context);
       db.deleteCat(NameCate);


        categories.remove(position);
        notifyItemRemoved(position);
        //enables smooth animations and also updates data
        notifyItemRangeChanged(position, getItemCount());


    }

}
