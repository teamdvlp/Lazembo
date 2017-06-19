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
import com.example.dangminhtien.lazembo.data.Sanpham;
import com.example.dangminhtien.lazembo.data.get_set_sanpham;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;

public class SecondMainAcitivty extends MainActivity {
    ViewPager vpg_container;
    TabLayout tbl_list;
    ViewpagerAdapter adapter;
    get_set_sanpham get_set_sanpham;
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
        get_sanphams();
        overridePendingTransition(R.anim.appear,R.anim.disappear);
        addControlss();
        addEventss();
    }
    private void get_sanphams () {
        get_set_sanpham = new get_set_sanpham(SecondMainAcitivty.this);
        get_set_sanpham.get_all_ma_sanpham();
        get_set_sanpham.set_on_get_all_ma_sanpham(new get_set_sanpham.get_all_ma_sanpham() {
            @Override
            public void on_get_all_ma_sanpham(ArrayList<String> ma_sanpham_storage) {
                get_set_sanpham.get_sanphams(ma_sanpham_storage);
                get_set_sanpham.set_on_get_sanphams_listener(new get_set_sanpham.get_sanphams() {
                    @Override
                    public void on_get_sanphams(ArrayList<Sanpham> sanphams) {
                        Iterator<Sanpham> sanphamIterator = sanphams.iterator();
                    }                });
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addEventss() {
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
