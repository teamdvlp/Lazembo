package com.example.dangminhtien.lazembo.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dangminhtien.lazembo.Model.xu_ly_bottom_sheet;
import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.adapter.ExpandableListViewAdapter;
import com.example.dangminhtien.lazembo.adapter.ViewPagerBottomSheetAdapter;
import com.example.dangminhtien.lazembo.adapter.ViewpagerAdapter;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SecondMainAcitivty extends AppCompatActivity {
    ViewPager vpg_container;
    TabLayout tbl_list;
    ViewpagerAdapter viewpager_adapter;

    NestedScrollView bottomSheet;
    BottomSheetBehavior bottomSheetBehavior;

    Toolbar toolbar;
    DrawerLayout drawer_container;
    NavigationView nav_list_goods;
    ExpandableListView explv_list;
    ExpandableListViewAdapter explv_adapter;
    ArrayList<String> list_group_title;
    HashMap<String, ArrayList<String>>  list_sub_title;

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_main_acitivty);
        overridePendingTransition(R.anim.appear,R.anim.disappear);
        addControlss();
        addEventss();
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


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void addControlss() {
        //GHI NHỚ NHÉ
        addControl_fromMainActivity();

        bottomSheet = (NestedScrollView) findViewById(R.id.nestedSrollView);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        vpg_container = (ViewPager) findViewById(R.id.vpg_container);
        tbl_list = (TabLayout) findViewById(R.id.tbl_list_fragment_second);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewpager_adapter= new ViewpagerAdapter(fragmentManager);
        vpg_container.setAdapter(viewpager_adapter);
        tbl_list.setupWithViewPager(vpg_container);
    }

        //GHI NHỚ NHÉ
    private void addControl_fromMainActivity() {

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        drawer_container = (DrawerLayout) findViewById(R.id.drawer_container_second);
        nav_list_goods = (NavigationView) findViewById(R.id.nav_list_goods_second);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer_container,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer_container.setDrawerListener(toggle);
        toggle.syncState();

        list_group_title = new ArrayList<>();
        list_sub_title = new HashMap<>();
        prepareDataForExpListView();
        explv_list = (ExpandableListView) findViewById(R.id.explv_list_goods_second);
        explv_adapter = new ExpandableListViewAdapter(this,
                list_group_title,
                list_sub_title);
        explv_list.setAdapter(explv_adapter);


    }
        //GHI NHỚ NHÉ
    public void prepareDataForExpListView() {
        list_group_title.add("Thời trang");
        list_group_title.add("Thức ăn");
        list_group_title.add("Thiết bị điện tử");
        list_group_title.add("Thiết bị gia dụng");
        list_group_title.add("Xe hơi");
        list_group_title.add("Khuyến mãi - Deal");

        ArrayList<String> fashion_list = new ArrayList<>();
        fashion_list.add("Nam");
        fashion_list.add("Nữ");
        fashion_list.add("Quảng cáo");
        list_sub_title.put(list_group_title.get(0), fashion_list);

        ArrayList<String> food_list = new ArrayList<>();
        food_list.add("Chay");
        food_list.add("Mặn");
        list_sub_title.put(list_group_title.get(1), food_list);

        ArrayList<String> electricity_list = new ArrayList<>();
        electricity_list.add("Desktop");
        electricity_list.add("Laptop");
        electricity_list.add("Linh kiện");
        electricity_list.add("Phụ kiện");
        list_sub_title.put(list_group_title.get(2), electricity_list);

        ArrayList<String> houseware_list = new ArrayList<>();
        houseware_list.add("Bếp");
        houseware_list.add("Chảo");
        houseware_list.add("Nồi");
        houseware_list.add("Dao - kéo");
        houseware_list.add("Chăn - gối");
        houseware_list.add("Đệm");
        list_sub_title.put(list_group_title.get(3), houseware_list);

        ArrayList<String> car_list = new ArrayList<>();
        car_list.add("Phụ kiện");
        car_list.add("Phụ tùng");
        car_list.add("Quảng cáo");
        car_list.add("Thương Hiệu");
        list_sub_title.put(list_group_title.get(4), car_list);

        ArrayList<String> sale_off_list = new ArrayList<>();
        sale_off_list.add("Sản phẩm giảm giá");
        sale_off_list.add("Sản phẩm có quà tặng");
        list_sub_title.put(list_group_title.get(5), sale_off_list);

    }

}
