package com.example.dangminhtien.lazembo.Model;

import android.app.Activity;
import com.example.dangminhtien.lazembo.adapter.adapter_sp_account;

import android.content.Intent;
import com.example.dangminhtien.lazembo.activity.activity_Login_Register;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.adapter.ViewPagerBottomSheetAdapter;
import com.example.dangminhtien.lazembo.data.get_set_Khachhang;
import com.google.firebase.auth.FirebaseAuth;

import java.net.MalformedURLException;

public class xu_ly_bottom_sheet {
    private NestedScrollView nestedScrollView;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private BottomSheetBehavior bottomSheetBehavior;
    private FragmentManager fragmentManager;
    private Activity activity;

    public xu_ly_bottom_sheet( NestedScrollView nestedScrollView, FragmentManager supportFragmentManager, Activity parent) {
        this.nestedScrollView = nestedScrollView;
        this.activity = parent;
        this.fragmentManager = supportFragmentManager;
    }

    public void xuly () throws MalformedURLException {
        bottomSheetBehavior = BottomSheetBehavior.from(nestedScrollView);
        bottomSheetBehavior.setPeekHeight(140);

        viewPager = (ViewPager) activity.findViewById(R.id.vpg_bottom_sheet);
        tabLayout = (TabLayout) activity.findViewById(R.id.tbl_bottom_sheet);

        ViewPagerBottomSheetAdapter viewPagerBottomSheetAdapter = new ViewPagerBottomSheetAdapter(fragmentManager);
        viewPager.setAdapter(viewPagerBottomSheetAdapter);
        tabLayout.setupWithViewPager(viewPager);

        TabLayout.Tab tab1 = tabLayout.getTabAt(0);
        tab1.setCustomView(getView(0));
        TabLayout.Tab tab2 = tabLayout.getTabAt(1);
        tab2.setCustomView(getView(1));
    }




    private View getView(int position){
        View view = activity.getLayoutInflater().inflate(R.layout.tab_custom, null);
        ImageView img_icon = (ImageView) view.findViewById(R.id.imgv_icon);
        if(position == 0){
            img_icon.setImageResource(R.drawable.icon_cart_2);
        } else if( position == 1){
            img_icon.setImageResource(R.drawable.icon_account_2);
        }
        return view;
    }
}
