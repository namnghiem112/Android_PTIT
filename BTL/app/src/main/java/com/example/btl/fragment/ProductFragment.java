package com.example.btl.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.btl.Home;
import com.example.btl.R;
import com.example.btl.adapter.ProductAdapter;
import com.example.btl.adapter.SlidePhotoAdapter;
import com.example.btl.model.Product;
import com.example.btl.model.SlidePhoto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class ProductFragment extends Fragment {

    private Home home;
    //    để hẹn giờ cho slide chạy đúng
    private Timer mTimer;
    private List<SlidePhoto> listSlidePhoto;
    private List<Product> listAllProduct;

    private View mView;
    private RecyclerView rcvProduct;
    private ViewPager viewPagerSlidePhoto;
    private CircleIndicator circleIndicator;
    private AutoCompleteTextView atcProductSearch;

    private ProductAdapter productAdapter;
    private SlidePhotoAdapter slidePhotoAdapter;

    public ProductFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_product, container, false);
        // Khởi tạo các item
        initItem();

        // Set Adapter cho viewPagerSlidePhoto
        setDataSlidePhotoAdapter();

        // Set Adapter cho rcvProduct
        setDataProductAdapter();

        return mView;
    }

    // Khởi tạo các item
    private void initItem() {
        rcvProduct = mView.findViewById(R.id.rcv_product);
        viewPagerSlidePhoto = mView.findViewById(R.id.vp_slide_photo);
        circleIndicator = mView.findViewById(R.id.circle_indicator);
        atcProductSearch = mView.findViewById(R.id.atc_product_search);

        listSlidePhoto = getListSlidePhoto();
        listAllProduct = getDataProduct();

        home = (Home) getActivity();
    }

    // Lấy Product để vào slide
    private List<SlidePhoto> getListSlidePhoto() {
        List<SlidePhoto> listSlidePhoto = new ArrayList<>();
        listSlidePhoto.add(new SlidePhoto(R.drawable.slide1));
        listSlidePhoto.add(new SlidePhoto(R.drawable.slide2));
        listSlidePhoto.add(new SlidePhoto(R.drawable.slide3));
        listSlidePhoto.add(new SlidePhoto(R.drawable.slide4));
        listSlidePhoto.add(new SlidePhoto(R.drawable.slide5));
        return listSlidePhoto;
    }

    // Set Adapter cho viewPagerSlidePhoto
    private void setDataSlidePhotoAdapter() {
        slidePhotoAdapter = new SlidePhotoAdapter(listSlidePhoto, this);
        viewPagerSlidePhoto.setAdapter(slidePhotoAdapter);
//        set ảnh vào các chấm con của circle
        circleIndicator.setViewPager(viewPagerSlidePhoto);
//        set circleIndicator vào slide
        slidePhotoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

//        // Auto chuyển các slide photo
        autoSildeImage();
    }

    // Auto chuyển các slide photo
    private void autoSildeImage() {
        if (listSlidePhoto == null || listSlidePhoto.isEmpty() || viewPagerSlidePhoto == null) {
            return;
        }
        if (mTimer == null) {
            mTimer = new Timer();
        }
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPagerSlidePhoto.getCurrentItem();
                        int totalItem = listSlidePhoto.size() - 1;

                        // Nếu item hiện tại chưa phải cuối cùng
                        if (currentItem < totalItem) {
                            currentItem++;
                            viewPagerSlidePhoto.setCurrentItem(currentItem);
                        } else {
                            viewPagerSlidePhoto.setCurrentItem(0);
                        }
                    }
                });
            }

            // xử lý thêm để set time
        }, 500, 3000);
    }

    // Set Adapter cho rcvProduct
    private void setDataProductAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(home, 2);
        rcvProduct.setLayoutManager(gridLayoutManager);
        productAdapter = new ProductAdapter();
        atcProductSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i("A", "change");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().isEmpty()) {
                    getDataProduct();
                } else {
                    searchData(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.i("C", "change");
            }
        });
        rcvProduct.setAdapter(productAdapter);
    }

    // Lấy dữ liệu Product từ FireBase
    private List<Product> getDataProduct() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("DbProduct");
        List<Product> mListProduct = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mListProduct.clear();
                Log.i("Snapshot", snapshot.toString());
                for (DataSnapshot data : snapshot.getChildren()) {
                    Product product = data.getValue(Product.class);
                    product.setId(data.getKey());
                    mListProduct.add(product);
                }
                productAdapter.setData(mListProduct, home);
                productAdapter.notifyDataSetChanged();
//                setProductSearchAdapter(mListProduct);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Không tải được dữ liệu từ firebase"
                        + error.toString(), Toast.LENGTH_LONG).show();
                Log.d("MYTAG", "onCancelled" + error.toString());
            }
        });
        return mListProduct;
    }

    private List<Product> searchData(String name) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("DbProduct");
        List<Product> mListProduct = new ArrayList<>();
        Log.i("AAAAA", "AAAA");
        // Lấy thông tin order
        myRef.orderByChild("productName").startAt(name)
                .endAt(name+"\uf8ff")

                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Log.i("Snapshot", snapshot.toString());
                        mListProduct.clear();
                        for (DataSnapshot data : snapshot.getChildren()) {
                            Log.i("data", data.getKey());
                            Product product = data.getValue(Product.class);
                            product.setId(data.getKey());
                            mListProduct.add(product);
                        }
                        productAdapter.setData(mListProduct, home);
                        productAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), "Không lấy được thông tin đơn hàng từ firebase", Toast.LENGTH_SHORT).show();
                    }
                });
        return mListProduct;
    }
}
