package com.example.btl.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.Home;
import com.example.btl.R;
import com.example.btl.adapter.HistoryProductAdapter;
import com.example.btl.model.DetailOrder;
import com.example.btl.model.Order;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
    private Home home;
    private List<Order> listOrder;
    private List<DetailOrder> listDetailOrder;

    private View mView;
    private RecyclerView rcvHitorySearch;

    private HistoryProductAdapter historyProductAdapter;

    public HistoryFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_history,container,false);
        initItem();
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
    // Khởi tạo các item
    private void initItem(){
        listOrder = new ArrayList<>();
        listDetailOrder = new ArrayList<>();

        home = (Home) getActivity();

        historyProductAdapter = new HistoryProductAdapter();
        rcvHitorySearch = mView.findViewById(R.id.rcv_hitory_search);
        findOrder();
    }

    // Lấy thông tin order
    private void findOrder(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
        // Clear các list dữ liệu khi tìm kiếm
        listOrder.clear();
        listDetailOrder.clear();

        // Kết nối tới data base
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("DbOrder");
//        String phone = edtHistoryPhone.getText().toString();
//        String phoneRegex = "^0\\d{9}$";
//        Pattern phonePattern = Pattern.compile(phoneRegex);
//        Matcher phoneMatcher = phonePattern.matcher(phone);
//
//        if (phone.isEmpty() || !phoneMatcher.matches()) {
//            Toast.makeText(getContext(), "Số điện thoại nhập không hợp lệ", Toast.LENGTH_SHORT).show();
//            return;
//        }

        // Lấy thông tin order
        myRef.orderByChild("email").equalTo(email)
                .addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        historyProductAdapter.notifyDataSetChanged();
                        for (DataSnapshot dataOrder : snapshot.getChildren()){
                            Log.i("dataOder", dataOrder.toString());
                            Log.i("dataOder", dataOrder.getValue(Order.class).toString());
                            Log.i("dataOder", dataOrder.getKey());
                            Order order = dataOrder.getValue(Order.class);
                            order.setOrderNo(dataOrder.getKey());
                            listOrder.add(order);
                        }

                        if (listOrder.size() > 0){
                            // Lấy thông tin detail order
                            findDetailOrder(myRef);
                        }
                        else {
                            Toast.makeText(getContext(),"Không tìm thấy lịch sử đặt hàng",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(),"Không lấy được thông tin đơn hàng từ firebase",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Lấy thông tin detail order
    private void findDetailOrder( DatabaseReference myRef){
        if (listOrder.size() > 0){
            for (int i = 0; i<listOrder.size(); i++){
                Order order = listOrder.get(i);
                myRef.child(order.getOrderNo()).child("detail").orderByChild("orderNo").equalTo(order.getOrderNo())
                        .addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataDetail : snapshot.getChildren()){
                                    historyProductAdapter.notifyDataSetChanged();
                                    DetailOrder detailOrder = dataDetail.getValue(DetailOrder.class);
                                    listDetailOrder.add(detailOrder);
                                }

                                // set data HistoryProductAdapter
                                if (listDetailOrder.size() > 0){
                                    setDataHistoryProductAdapter();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(getContext(),"Không lấy được chi tiết đơn hàng từ firebase",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }
    }

    // set data cho HistoryProductAdapter
    private void setDataHistoryProductAdapter(){
        Log.i("-------------------data", listOrder.size()+"");
        Log.i("-------------------data -de", listDetailOrder.size()+"");
        historyProductAdapter.setData(listDetailOrder,listOrder,home);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(home,RecyclerView.VERTICAL,false);
        rcvHitorySearch.setLayoutManager(linearLayoutManager);
        rcvHitorySearch.setAdapter(historyProductAdapter);
    }
}
