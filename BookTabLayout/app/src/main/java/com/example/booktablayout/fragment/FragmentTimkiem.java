package com.example.booktablayout.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booktablayout.AddActivity;
import com.example.booktablayout.R;
import com.example.booktablayout.UpdateDeleteActivity;
import com.example.booktablayout.adapter.RecycleViewAdapter;
import com.example.booktablayout.dal.SQLiteHelper;
import com.example.booktablayout.model.Book;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FragmentTimkiem extends Fragment implements RecycleViewAdapter.ItemListener{
    private EditText edngaybatdau, edngayketthuc;
    private TextView textthongke;
    private Button btnSearch, btnThongKe;
    private CheckBox cbVN, cbTG;
    private RecyclerView recyclerView;
    RecycleViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timkiem,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initview(view);
        adapter = new RecycleViewAdapter();
        SQLiteHelper db = new SQLiteHelper(getContext());
//        adapter.setList(db.getAll());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
//        edngaybatdau.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar calendar = Calendar.getInstance();
//                int year = calendar.get(Calendar.YEAR);
//                int month = calendar.get(Calendar.MONTH);
//                int day = calendar.get(Calendar.DAY_OF_MONTH);
//                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
//                        m++;
//                        String date = d+"/"+m+"/"+y;
//                        edngaybatdau.setText(date);
//                    }
//                },year, month, day);
//                datePickerDialog.show();
//            }
//        });
//        edngayketthuc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar calendar = Calendar.getInstance();
//                int year = calendar.get(Calendar.YEAR);
//                int month = calendar.get(Calendar.MONTH);
//                int day = calendar.get(Calendar.DAY_OF_MONTH);
//                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
//                        m++;
//                        String date = d+"/"+m+"/"+y;
//                        edngayketthuc.setText(date);
//                    }
//                },year, month, day);
//                datePickerDialog.show();
//            }
//        });
        adapter.setItemListener(this);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ngaybatdau = edngaybatdau.getText().toString();
                String ngaykethuc = edngayketthuc.getText().toString();
                Log.i("kiemtra2111",ngaybatdau);
                SQLiteHelper db = new SQLiteHelper(getContext());
                List<Book> list = db.getAll();
                List<Book> list1 = new ArrayList<>();
                for (Book b : list){
                    String s = b.getNgayxuathien().split("/",2)[1];
                    if(s.compareTo(ngaybatdau)>=0 && s.compareTo(ngaykethuc)<=0){
                        Log.i("kiemtra2112",ngaybatdau);
                        list1.add(b);
                    }
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                        if(isDateBetween(b.getNgayxuathien(),ngaybatdau,ngaykethuc)){
//                            list1.add(b);
//                        }
//                    }
                }
                adapter.setList(list1);
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteHelper db = new SQLiteHelper(getContext());
                List<Book> list = db.getAll();
                List<Book> list1 = new ArrayList<>();
                int vnarn = 0, pros=0, pron =0;
                int tgarn = 0, tgpros =0 , tgpron=0;
                long sum =0;
                if(cbVN.isChecked()){
                    for (Book b : list){
                        if(b.getCautruc().contains("ARN")) vnarn += Integer.parseInt(b.getSoluongVN());
                        else if(b.getCautruc().contains("PRO_S")) pros+= Integer.parseInt(b.getSoluongVN());
                        else if(b.getCautruc().contains("PRO_N")) pron+= Integer.parseInt(b.getSoluongVN());
                    }
                    String res = "ARN co "+ vnarn +"\n PRO_S co "+ pros +"\n PRO_N co "+pron;
                    textthongke.setText(res);
                }
                else {
                    for (Book b : list){
                        if(b.getCautruc().contains("ARN")) tgarn+= Integer.parseInt(b.getSoluongTG());
                        else if(b.getCautruc().contains("PRO_S")) tgpros+= Integer.parseInt(b.getSoluongTG());
                        else if(b.getCautruc().contains("PRO_N")) tgpron+= Integer.parseInt(b.getSoluongTG());
                    }
                    String res = "ARN co "+ tgarn +"\n PRO_S co "+ tgpros +"\n PRO_N co "+tgpron;
                    textthongke.setText(res);
                }
//                adapter.setList(list);
            }
        });
    }

    public void initview(View v){
        cbVN = v.findViewById(R.id.cbVN);
        edngaybatdau = v.findViewById(R.id.ngaybatdau);
        edngayketthuc = v.findViewById(R.id.ngayketthuc);
        btnSearch = v.findViewById(R.id.btSearch);
        recyclerView = v.findViewById(R.id.recycleView);
        btnThongKe = v.findViewById(R.id.btthongke);
        textthongke = v.findViewById(R.id.textthongke1);
    }
    @Override
    public void onItemClick(View view, int position) {
        Book item = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("item",item);
        startActivity(intent);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean isDateBetween(String dateStr, String startStr, String endStr) {
        // Định dạng ngày
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            // Chuyển đổi chuỗi ngày thành đối tượng LocalDate
            LocalDate date = LocalDate.parse(dateStr, formatter);
            LocalDate startDate = LocalDate.parse(startStr, formatter);
            LocalDate endDate = LocalDate.parse(endStr, formatter);

            // Kiểm tra ngày có nằm giữa startDate và endDate không
            return (date.isEqual(startDate) || date.isAfter(startDate)) &&
                    (date.isEqual(endDate) || date.isBefore(endDate));
        } catch (DateTimeParseException e) {
            System.out.println("Định dạng ngày không hợp lệ: " + e.getMessage());
            return false;
        }
    }
}
