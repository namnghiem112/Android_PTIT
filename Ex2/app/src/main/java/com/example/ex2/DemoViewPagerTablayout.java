package com.example.ex2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

public class DemoViewPagerTablayout extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Button btPre, btNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_view_pager_tablayout);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabLayout);
        btPre = findViewById(R.id.btPre);
        btNext = findViewById(R.id.btNext);
        btPre.setOnClickListener(this);
        btNext.setOnClickListener(this);
        FragmentManager manager = getSupportFragmentManager();
        FragmentAdapter adapter = new FragmentAdapter(manager,3);
        // Tạo hiệu ứng cho chuyển trang
        viewPager.setPageTransformer(true,new HorizontalFlipTransformation());


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setTablayoutTitleColor();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        tabLayout.setTabTextColors(Color.BLACK,getResources().getColor(R.color.colorBlink));
                        btNext.setBackgroundColor(getResources().getColor(R.color.colorBlink));
                        btPre.setBackgroundColor(getResources().getColor(R.color.colorBlink));
                        break;
                    case 1:
                        tabLayout.setTabTextColors(Color.BLACK,getResources().getColor(R.color.colorGreen));
                        btNext.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        btPre.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        break;
                    case 2:
                        tabLayout.setTabTextColors(Color.BLACK,getResources().getColor(R.color.colorBlue));
                        btNext.setBackgroundColor(getResources().getColor(R.color.colorBlue));
                        btPre.setBackgroundColor(getResources().getColor(R.color.colorBlue));
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        int viewID = v.getId();
        if(viewID==R.id.btNext){
            if(viewPager.getCurrentItem()==2){
                return;
            }
            else {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                setTablayoutTitleColor();
            }
        }
        else {
            if(viewPager.getCurrentItem()==0){
                return;
            }
            else {
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
                setTablayoutTitleColor();
            }
        }
    }

    private void setTablayoutTitleColor() {
        switch (viewPager.getCurrentItem()){
            case 0:
                tabLayout.setTabTextColors(Color.BLACK,getResources().getColor(R.color.colorBlink));
                break;
            case 1:
                tabLayout.setTabTextColors(Color.BLACK,getResources().getColor(R.color.colorGreen));
                break;
            case 2:
                tabLayout.setTabTextColors(Color.BLACK,getResources().getColor(R.color.colorBlue));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(viewPager.getCurrentItem()==0){
            super.onBackPressed();
        }
        else viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
    }
    // trasition hiệu ứng khi chuyển trang
    public class HorizontalFlipTransformation implements ViewPager.PageTransformer {
        @Override
        public void transformPage(@NonNull View page, float position) {
            // Ensure that the page is not on the left or right of the screen
            if (position < -1 || position > 1) {
                page.setAlpha(0f);
                return;
            }

            // Calculate the rotation angle based on the position
            float rotation = 180f * position;

            // Set pivot points for rotation (center of the page)
            page.setPivotX(page.getWidth() * 0.5f);
            page.setPivotY(page.getHeight() * 0.5f);

            // Apply rotation transformation
            page.setRotationY(rotation);

            // Fade the page in or out based on its position
            page.setAlpha(Math.max(0f, 1f - Math.abs(position)));
        }
    }
}