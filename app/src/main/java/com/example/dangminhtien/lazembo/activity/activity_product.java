package com.example.dangminhtien.lazembo.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.adapter.adapter_product;

public class activity_product extends AppCompatActivity {

    ViewPager pagerCustomsp;
    TabLayout tabTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_seller);
        addControls();
    }

    private void addControls() {
        pagerCustomsp = (ViewPager) findViewById(R.id.Pager_CustomSp);
        tabTitle = (TabLayout) findViewById(R.id.tabTitle);
        adapter_product adapterSeller = new adapter_product(getSupportFragmentManager());
        pagerCustomsp.setAdapter(adapterSeller);

    }

}
