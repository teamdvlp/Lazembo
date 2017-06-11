package com.example.dangminhtien.lazembo.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dangminhtien.lazembo.Model.xu_ly_bottom_sheet;
import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.adapter.ViewPagerBottomSheetAdapter;
import com.example.dangminhtien.lazembo.adapter.ViewpagerAdapter;

import java.net.MalformedURLException;

public class SecondMainAcitivty extends MainActivity {
    ViewPager vpg_container;
    TabLayout tbl_list;
    ViewpagerAdapter adapter;

    NestedScrollView bottomSheet;
    BottomSheetBehavior bottomSheetBehavior;

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_second_main_acitivty,null,false);
        drawer_container.addView(contentView,0);

        overridePendingTransition(R.anim.appear,R.anim.disappear);
        addControlss();
        addEventss();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainActivity.back_finish =  true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addEventss() {
        explv_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                drawer_container.closeDrawer(nav_list_goods);
                Intent intent = new Intent(SecondMainAcitivty.this,ListOfProduct.class);
                intent.putExtra("LoaiMatHang", list_sub_title.get(list_group_title.get(groupPosition)).get(childPosition));
                startActivity(intent);
                return false;
            }
        });
        xu_ly_bottom_sheet xu_ly_bottom_sheet = new xu_ly_bottom_sheet( bottomSheet, getSupportFragmentManager(),SecondMainAcitivty.this );
        try {
            xu_ly_bottom_sheet.xuly();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void addControlss() {
        bottomSheet = (NestedScrollView) findViewById(R.id.nestedSrollView);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        vpg_container = (ViewPager) findViewById(R.id.vpg_container);
        tbl_list = (TabLayout) findViewById(R.id.tbl_list_fragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new ViewpagerAdapter(fragmentManager);
        vpg_container.setAdapter(adapter);
        tbl_list.setupWithViewPager(vpg_container);
    }
}
