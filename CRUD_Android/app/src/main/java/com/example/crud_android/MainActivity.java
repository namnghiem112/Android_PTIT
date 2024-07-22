package com.example.crud_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.crud_android.model.Employee;
import com.example.crud_android.model.EmployeeAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EmployeeAdapter employeeAdapter;
    private EditText editmanv,edittennv;
    private Button btnAdd, btnUpdate;
    private RadioGroup radioGroup;
    private RadioButton radioButton_nam, radioButton_nu;
    private int currentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        employeeAdapter = new EmployeeAdapter(getList());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(employeeAdapter);
        employeeAdapter.setMyItemClickListener(new EmployeeAdapter.onMyItemClickListener() {
            @Override
            public void doSomething(int position) {
                currentPosition = position;
                editmanv.setText(employeeAdapter.employeeList.get(position).getManv());
                edittennv.setText(employeeAdapter.employeeList.get(position).getTennv());
                if(employeeAdapter.employeeList.get(position).getGioitinh()==Employee.FEMALE){
                    radioButton_nu.setChecked(true);
                }
                else radioButton_nam.setChecked(true);
                btnAdd.setEnabled(false);
                btnUpdate.setEnabled(true);
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Employee employee = new Employee();
                        String manv = editmanv.getText().toString();
                        String tennv = edittennv.getText().toString();
                        int radioId = radioGroup.getCheckedRadioButtonId();
                        String gioitinh = radioId == R.id.radio_nam ? Employee.MALE : Employee.FEMALE;
                        employee.setManv(manv);
                        employee.setTennv(tennv);
                        employee.setGioitinh(gioitinh);
                        employeeAdapter.upDateItem(currentPosition,employee);
                        editmanv.setText("");
                        edittennv.setText("");
                        radioButton_nam.setChecked(true);
                    }
                });
            }
        });
        btnAdd.setOnClickListener(v -> {
            Employee employee = new Employee();
            String manv = editmanv.getText().toString();
            String tennv = edittennv.getText().toString();
            int radioId = radioGroup.getCheckedRadioButtonId();
            String gioitinh = radioId == R.id.radio_nam ? Employee.MALE : Employee.FEMALE;
            employee.setManv(manv);
            employee.setTennv(tennv);
            employee.setGioitinh(gioitinh);
            employeeAdapter.addItem(employee);
            editmanv.setText("");
            edittennv.setText("");
            radioButton_nam.setChecked(true);
        });
    }

    private List<Employee> getList() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("123","Nghiem Nam",Employee.MALE));
        employeeList.add(new Employee("124","Nghiem Nam",Employee.FEMALE));
        employeeList.add(new Employee("123","Nghiem Nam",Employee.MALE));
        return employeeList;
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        editmanv = findViewById(R.id.editText_manv);
        edittennv = findViewById(R.id.editText_tennv);
        btnAdd = findViewById(R.id.btn_add);
        btnUpdate = findViewById(R.id.btn_update);
        btnUpdate.setEnabled(false);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton_nam = findViewById(R.id.radio_nam);
        radioButton_nu = findViewById(R.id.radio_nu);
    }
}