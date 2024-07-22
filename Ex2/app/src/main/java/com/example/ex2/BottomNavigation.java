package com.example.ex2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.ex2.adapter.ViewPagerAdapter;
import com.example.ex2.fragment.FragmentCafe;
import com.example.ex2.fragment.FragmentHome;
import com.example.ex2.fragment.FragmentNotification;
import com.example.ex2.fragment.FragmentSearch;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigation extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        viewPager = findViewById(R.id.viewPager);
        navigationView = findViewById(R.id.navigation);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),4);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        navigationView.getMenu().findItem(R.id.mHome).setCheckable(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.mNoti).setCheckable(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.mSearch).setCheckable(true);
                        break;
                    case 3:
                        navigationView.getMenu().findItem(R.id.mCafe).setCheckable(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int x = item.getItemId();
                if(x == R.id.mHome){
                    viewPager.setCurrentItem(0);
//                    replaceFragment(new FragmentHome());
                }
                else if(x == R.id.mNoti){
                    viewPager.setCurrentItem(1);
//                    replaceFragment(new FragmentNotification());
                }
                else if(x == R.id.mSearch){
                    viewPager.setCurrentItem(2);
//                    replaceFragment(new FragmentSearch());
                }
                else if(x == R.id.mCafe){
                    viewPager.setCurrentItem(3);
//                    replaceFragment(new FragmentCafe());
                }
                return true;
            }
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewPager,fragment);
        fragmentTransaction.commit();
    }
}