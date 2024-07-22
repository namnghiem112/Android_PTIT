package com.example.book_crud;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private Context context;
    List<Book> bookList;
    List<Book> bookBackUp;
    public BookAdapter() {
    }

    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
        this.bookBackUp = new ArrayList<>();
    }

    public void setBookBackUp(List<Book> bookBackUp) {
        this.bookBackUp = bookBackUp;
    }
    public List<Book> getBookBackUp() {
        return bookBackUp;
    }
    public void updateItem(int i, Book book2) {
        bookList.set(i,book2);
        bookBackUp.set(i ,book2);
        notifyDataSetChanged();
    }
    public void fillterList(List<Book> fillter){
        this.bookList = fillter;
        notifyDataSetChanged();
    }
    public interface myItemClick {
        void doSomething(int i);
    }
    private myItemClick myItemClick;

    public void setMyItemClick(BookAdapter.myItemClick myItemClick) {
        this.myItemClick = myItemClick;
    }
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Book book = bookList.get(position);
        holder.imageView.setImageResource(book.getImg());
        holder.textView.setText(book.getMasach()+" "+book.getTensach());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(book);
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myItemClick.doSomething(position);
            }
        });
    }

    private void deleteItem(Book book) {
        bookList.remove(book);
        bookBackUp.remove(book);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return bookList!=null ? bookList.size():0;
    }

    public void addItem(Book book) {
        bookList.add(book);
        bookBackUp.add(book);
        notifyDataSetChanged();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private Button btnDelete;
        private CardView cardView;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_img);
            textView = itemView.findViewById(R.id.item_text);
            btnDelete = itemView.findViewById(R.id.item_btndelete);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
