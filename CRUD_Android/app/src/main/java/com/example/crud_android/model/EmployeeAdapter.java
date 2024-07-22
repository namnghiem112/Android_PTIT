package com.example.crud_android.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud_android.R;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>{
    // Bước 1 tạo interface
    public interface onMyItemClickListener {
        void doSomething(int position);
    }
    // Bước 2 tạo biến theo interface và hàm set
    private onMyItemClickListener myItemClickListener;

    public void setMyItemClickListener(onMyItemClickListener myItemClickListener) {
        this.myItemClickListener = myItemClickListener;
    }

    private Context context;
    public List<Employee> employeeList;
    public EmployeeAdapter() {
    }

    public EmployeeAdapter(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public EmployeeAdapter(Context context, List<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Employee employee = employeeList.get(position);
        int imgId = employee.getGioitinh() == Employee.MALE ? R.drawable.male : R.drawable.famale;
        holder.img.setImageResource(imgId);
        holder.textView.setText(employee.getManv()+" - "+employee.getTennv());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myItemClickListener.doSomething(position);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(employee);
            }
        });
    }

    @Override
    public int getItemCount() {
        return employeeList != null ? employeeList.size() : 0;
    }
    public void addItem(Employee employee){
        employeeList.add(employee);
        notifyDataSetChanged();
    }
    public void upDateItem(int position ,Employee employee){
        employeeList.set(position,employee);
        notifyDataSetChanged();
    }
    public void deleteItem(Employee employee){
        employeeList.remove(employee);
        notifyDataSetChanged();
    }
    public class EmployeeViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView textView;
        private Button btnDelete;
        private CardView cardView;
        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_img_view);
            textView = itemView.findViewById(R.id.item_text);
            btnDelete = itemView.findViewById(R.id.item_btn_delete);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
