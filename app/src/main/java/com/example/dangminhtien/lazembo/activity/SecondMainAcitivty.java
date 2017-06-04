package com.example.dangminhtien.lazembo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.adapter.ViewPagerBottomSheetAdapter;
import com.example.dangminhtien.lazembo.adapter.ViewpagerAdapter;

public class SecondMainAcitivty extends MainActivity {
    ViewPager vpg_container,vpg_bottom_sheet;
    TabLayout tbl_list,tbl_bottom_sheet;
    ViewpagerAdapter adapter;
    ViewPagerBottomSheetAdapter adapter_bottom_sheet;

    NestedScrollView bottomSheet;
    BottomSheetBehavior bottomSheetBehavior;

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

        tbl_bottom_sheet.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED){
                    bottomSheetBehavior.setPeekHeight(140);
                }else if(newState == BottomSheetBehavior.STATE_EXPANDED){
                    bottomSheetBehavior.setPeekHeight(140);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    private void addControlss() {
        bottomSheet = (NestedScrollView) findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        vpg_container = (ViewPager) findViewById(R.id.vpg_container);
        tbl_list = (TabLayout) findViewById(R.id.tbl_list_fragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new ViewpagerAdapter(fragmentManager);
        vpg_container.setAdapter(adapter);
        tbl_list.setupWithViewPager(vpg_container);

        vpg_bottom_sheet = (ViewPager) findViewById(R.id.vpg_bottom_sheet);
        tbl_bottom_sheet = (TabLayout) findViewById(R.id.tbl_bottom_sheet);
        FragmentManager fragmentManager2 = getSupportFragmentManager();
        adapter_bottom_sheet = new ViewPagerBottomSheetAdapter(fragmentManager2);
        vpg_bottom_sheet.setAdapter(adapter_bottom_sheet);
        tbl_bottom_sheet.setupWithViewPager(vpg_bottom_sheet);

    }
}
