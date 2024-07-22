package com.example.book_crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private Spinner spinner;
    private EditText editTextMaSach, editTextTenSach;
    private Button btnAdd, btnUpdate;
    private RecyclerView recyclerView;
    private SpinnerAdapter adapter;
    private BookAdapter bookAdapter;
    private int currentPosition;
    private SearchView searchView;
    int[] spinnerList ={R.drawable.img,R.drawable.img_1,R.drawable.img_2,R.drawable.img_3,R.drawable.img_4,R.drawable.img_5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        spinner = findViewById(R.id.spinner);
        adapter = new SpinnerAdapter(MainActivity.this, spinnerList);
        spinner.setAdapter(adapter);
        bookAdapter = new BookAdapter(getBookList());
        bookAdapter.setBookBackUp(getBookList());
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(bookAdapter);
        btnAdd.setOnClickListener(v -> {
            String img = spinner.getSelectedItem().toString();
            int imgID = spinnerList[Integer.parseInt(img)];
            String masach = editTextMaSach.getText().toString();
            String tensach = editTextTenSach.getText().toString();
            Book book = new Book(imgID,masach,tensach);
            bookAdapter.addItem(book);
            editTextTenSach.setText("");
            editTextMaSach.setText("");
        });
        bookAdapter.setMyItemClick(new BookAdapter.myItemClick() {
            @Override
            public void doSomething(int i) {
                currentPosition = i;
                Book book = bookAdapter.bookList.get(i);
                int id =  book.getImg() - bookAdapter.bookList.get(0).getImg();
                Log.v("kiemtra",String.valueOf(book));
                spinner.setSelection(id);
                editTextMaSach.setText(book.getMasach());
                editTextTenSach.setText(book.getTensach());
                btnAdd.setEnabled(false);
                btnUpdate.setEnabled(true);
                btnUpdate.setOnClickListener(v -> {
                    String img = spinner.getSelectedItem().toString();
                    int imgID = spinnerList[Integer.parseInt(img)];
                    String masach = editTextMaSach.getText().toString();
                    String tensach = editTextTenSach.getText().toString();
                    book.setImg(imgID);
                    book.setMasach(masach);
                    book.setTensach(tensach);
                    bookAdapter.updateItem(i,book);
                    editTextTenSach.setText("");
                    editTextMaSach.setText("");
                    btnAdd.setEnabled(true);
                    btnUpdate.setEnabled(false);
                });
            }
        });
        searchView.setOnQueryTextListener(this);
    }

    private List<Book> getBookList() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(R.drawable.img,"123","Tâm lý học tội phạm"));
        bookList.add(new Book(R.drawable.img_1,"124","Nhà giả kim"));
        bookList.add(new Book(R.drawable.img_2,"125","Muôn kiếp nhân sinh"));
        bookList.add(new Book(R.drawable.img_3,"126","Tuổi trẻ đáng giá bao nhiêu"));
        bookList.add(new Book(R.drawable.img_4,"127","Thay đổi suy nghĩ thay đổi cả cuộc đời"));
        bookList.add(new Book(R.drawable.img_5,"128","Không diệt không sinh đừng sợ hãi"));
        return bookList;
    }

    private void initView() {
        spinner = findViewById(R.id.spinner);
        editTextMaSach = findViewById(R.id.masach);
        editTextTenSach = findViewById(R.id.tensach);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        recyclerView = findViewById(R.id.recy_view);
        btnUpdate.setEnabled(false);
        searchView = findViewById(R.id.search);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<Book> fillterList = new ArrayList<>();
        for (Book i : bookAdapter.getBookBackUp()){
            if(i.getTensach().toLowerCase().contains(newText.toLowerCase())){
                fillterList.add(i);
            }
        }
        if(fillterList.isEmpty()){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }
        else {
            bookAdapter.fillterList(fillterList);
        }
        return false;
    }
}