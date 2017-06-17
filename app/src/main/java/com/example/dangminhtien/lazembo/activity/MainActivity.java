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
import com.example.dangminhtien.lazembo.custom_view.*;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.dangminhtien.lazembo.R;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    static boolean continues = true;
    static boolean back_finish = false;
    ImageView img_introView;
    Toolbar toolbar;
    DrawerLayout drawer_container;
    NavigationView nav_list_goods;
    ArrayList<String> list_group_title;
    private ScrollView expan_custom;
    HashMap<String, ArrayList<String>>  list_sub_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
            addControls();
            addEvents();

        if(continues){
            changes(MainActivity.this, SecondMainAcitivty.class);
            finish();
            continues = false;
        }
            img_introView.setVisibility(View.GONE);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void addEvents() {
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
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_parent_nav);
        tiendvlp_expan_tree_listview tiendvlp_expan_tree_listview = new tiendvlp_expan_tree_listview(MainActivity.this, MainActivity.this);
        tiendvlp_expan_tree_listview.add_tree_node(new tree_node("/Thời trang", "Thời trang"));
        tiendvlp_expan_tree_listview.add_tree_node(new tree_node("/Thời trang/Nam/", "Nam"));
        tiendvlp_expan_tree_listview.add_tree_node(new tree_node("/Thời trang/Nam/Quần", "Quần"));
        tiendvlp_expan_tree_listview.add_tree_node(new tree_node("/Thời trang/Nam/Quần/Quần Kaki", "Quần Kaki"));
        tiendvlp_expan_tree_listview.add_tree_node(new tree_node("/Thời trang/Nam/Quần/Quần Jean", "Quần Jean"));
        tiendvlp_expan_tree_listview.add_tree_node(new tree_node("/Thời trang/Nam/Áo", "Áo"));
        tiendvlp_expan_tree_listview.add_tree_node(new tree_node("/Thời trang/Nam/Áo/Áo tay dài", "Áo tay dài"));
        tiendvlp_expan_tree_listview.init();
        linearLayout.addView(tiendvlp_expan_tree_listview);
    }

    protected void changes(Context context, Class classes){
        startActivity(new Intent(context,classes));
    }

}




