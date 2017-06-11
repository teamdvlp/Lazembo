package com.example.dangminhtien.lazembo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;


import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.adapter.ExpandableListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    static boolean continues = true;
    static boolean back_finish = false;
    ImageView img_introView;
    Toolbar toolbar;
    DrawerLayout drawer_container;
    NavigationView nav_list_goods;
    ExpandableListView explv_list;
    ExpandableListViewAdapter adapter;
    ArrayList<String> list_group_title;
    HashMap<String, ArrayList<String>> list_sub_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
            addControls();
            addEvents();

        if(continues){
            changes(MainActivity.this, SecondMainAcitivty.class);
            continues = false;
        }
            img_introView.setVisibility(View.GONE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(back_finish){
            changes(MainActivity.this, SecondMainAcitivty.class);
            back_finish = false;
        }
    }

    private void addEvents() {
        explv_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                startActivity(new Intent(MainActivity.this,ListOfProduct.class));

                return false;

            }
        });
    }

    private void addControls() {

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        drawer_container = (DrawerLayout) findViewById(R.id.drawer_container);
        nav_list_goods = (NavigationView) findViewById(R.id.nav_list_goods);
        img_introView = (ImageView) findViewById(R.id.img_introView);
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
        explv_list = (ExpandableListView) findViewById(R.id.explv_list_goods);
        adapter = new ExpandableListViewAdapter(this,
                list_group_title,
                list_sub_title);
        explv_list.setAdapter(adapter);


    }

    protected void changes(Context context, Class classes){
        startActivity(new Intent(context,classes));
    }

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




