package com.example.sach_crud;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sach_crud.model.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{
    private Context context;
    public List<Book> bookList;

    public BookAdapter() {
    }
    public interface myItemClick {
        void doSomething(int i);
    }
    private myItemClick myItemClick;

    public void setMyItemClick(BookAdapter.myItemClick myItemClick) {
        this.myItemClick = myItemClick;
    }
    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.textViewTen.setText(book.getTensach());
        holder.textViewNgayXB.setText(book.getNgayxuatban());
        Log.v("kiemtra",book.getTheloai());
        if(book.getTheloai().contains("tieuthuyet")){
            holder.checkBoxTieuThuyet.setChecked(true);
        }
        else holder.checkBoxTieuThuyet.setChecked(false);
        if(book.getTheloai().contains("thieunhi")){
            holder.checkBoxThieuNhi.setChecked(true);
        }
        else holder.checkBoxThieuNhi.setChecked(false);
        if(book.getTheloai().contains("khoahoc")){
            holder.checkBoxKhoaHoc.setChecked(true);
        }
        else holder.checkBoxKhoaHoc.setChecked(false);
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

    @Override
    public int getItemCount() {
        return bookList!=null ? bookList.size() : 0;
    }
    public void addItem(Book book) {
        bookList.add(book);
//        bookBackUp.add(book);
        notifyDataSetChanged();
    }
    private void deleteItem(Book book) {
        bookList.remove(book);
//        bookBackUp.remove(book);
        notifyDataSetChanged();
    }
    public void updateItem(int i, Book book2) {
        bookList.set(i,book2);
//        bookBackUp.set(i ,book2);
        notifyDataSetChanged();
    }
    public class BookViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTen, textViewNgayXB;
        private CheckBox checkBoxKhoaHoc, checkBoxTieuThuyet, checkBoxThieuNhi;
        private Button btnDelete;
        private CardView cardView;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTen = itemView.findViewById(R.id.item_textview);
            textViewNgayXB = itemView.findViewById(R.id.item_ngayxuatban);
            checkBoxKhoaHoc = itemView.findViewById(R.id.item_cbkhoahoc);
            checkBoxThieuNhi = itemView.findViewById(R.id.item_cbthieunhi);
            checkBoxTieuThuyet = itemView.findViewById(R.id.item_cbtieuthuyet);
            btnDelete = itemView.findViewById(R.id.item_btndelete);
            cardView = itemView.findViewById(R.id.item_cardview);
        }
    }
}
