package com.example.phamf.app_selling.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.phamf.app_selling.Adapters.ViewpagerAdapter;
import com.example.phamf.app_selling.R;

public class SecondMainAcitivty extends MainActivity {
    ViewPager vpg_container;
    TabLayout tbl_list;

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
    }

    private void addControlss() {
        vpg_container = (ViewPager) findViewById(R.id.vpg_container);
        tbl_list = (TabLayout) findViewById(R.id.tbl_list_fragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        ViewpagerAdapter adapter = new ViewpagerAdapter(fragmentManager);
        vpg_container.setAdapter(adapter);
        tbl_list.setupWithViewPager(vpg_container);
    }
}
