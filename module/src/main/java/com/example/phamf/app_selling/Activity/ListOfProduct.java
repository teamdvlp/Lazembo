package com.example.phamf.app_selling.Activity;


import android.content.Intent;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phamf.app_selling.Adapters.GridviewAdapter;
import com.example.phamf.app_selling.Model.ProductInformationModel;
import com.example.phamf.app_selling.R;

import java.util.ArrayList;


public class ListOfProduct extends MainActivity{
    TextView txt_type_of_goods;

    ArrayList<ProductInformationModel> gridv_arrayList_model;
    GridView gridv_list_goods;
    GridviewAdapter gridv_adapter;

    Spinner sp_tieu_chi;
    ArrayList<String> list_tieu_chi;
    ArrayAdapter<String> adapter_tieu_chi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_list_of_product,null,false);
        drawer_container.addView(contentView,0);
        overridePendingTransition(R.anim.appear,R.anim.disappear);
        addsControls();
        addEsvents();

        Bundle bundle = getIntent().getExtras();
        String loaiMatHang = (String) bundle.get("LoaiMatHang");
        txt_type_of_goods.setText(loaiMatHang);
    }
    private void addsControls() {
        txt_type_of_goods = (TextView) findViewById(R.id.txt_type_of_goods);

        gridv_arrayList_model = new ArrayList<>();
        prepareDataforGridview();
        gridv_list_goods = (GridView) findViewById(R.id.gridv_list_goods);
        gridv_adapter = new GridviewAdapter(gridv_arrayList_model,this);
        gridv_list_goods.setAdapter(gridv_adapter);

        list_tieu_chi = new ArrayList<>();
        prepareDataforSpinner();
        sp_tieu_chi = (Spinner) findViewById(R.id.sp_tieu_chi);
        adapter_tieu_chi = new ArrayAdapter<>(ListOfProduct.this,android.R.layout.simple_spinner_dropdown_item,list_tieu_chi);
        sp_tieu_chi.setAdapter(adapter_tieu_chi);
    }

    private void addEsvents() {
        explv_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                drawer_container.closeDrawer(nav_list_goods);
                txt_type_of_goods.setText(list_sub_title.get(list_group_title.get(groupPosition)).get(childPosition));
                return false;
            }
        });

        gridv_list_goods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changes(ListOfProduct.this,Cart.class);
            }
        });
    }

    private void prepareDataforGridview(){
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Gold","700$","",R.drawable.phone_iphone7_gold,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Black","710$","",R.drawable.phone_iphone7_black,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Plus Black","999$","",R.drawable.phone_iphone7_plus_black,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Plus Gold","950$","",R.drawable.phone_iphone7_plus_gold,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Plus Red","1050$","",R.drawable.phone_iphone7_plus_red,true));gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Gold","700$","",R.drawable.phone_iphone7_gold,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Black","710$","",R.drawable.phone_iphone7_black,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Plus Black","999$","",R.drawable.phone_iphone7_plus_black,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Plus Gold","950$","",R.drawable.phone_iphone7_plus_gold,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Plus Red","1050$","",R.drawable.phone_iphone7_plus_red,true));gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Gold","700$","",R.drawable.phone_iphone7_gold,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Black","710$","",R.drawable.phone_iphone7_black,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Plus Black","999$","",R.drawable.phone_iphone7_plus_black,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Plus Gold","950$","",R.drawable.phone_iphone7_plus_gold,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Plus Red","1050$","",R.drawable.phone_iphone7_plus_red,true)); gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Gold","700$","",R.drawable.phone_iphone7_gold,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Black","710$","",R.drawable.phone_iphone7_black,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Plus Black","999$","",R.drawable.phone_iphone7_plus_black,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Plus Gold","950$","",R.drawable.phone_iphone7_plus_gold,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Plus Red","1050$","",R.drawable.phone_iphone7_plus_red,true));gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Gold","700$","",R.drawable.phone_iphone7_gold,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Black","710$","",R.drawable.phone_iphone7_black,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Plus Black","999$","",R.drawable.phone_iphone7_plus_black,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Plus Gold","950$","",R.drawable.phone_iphone7_plus_gold,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Plus Red","1050$","",R.drawable.phone_iphone7_plus_red,true));gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Gold","700$","",R.drawable.phone_iphone7_gold,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Black","710$","",R.drawable.phone_iphone7_black,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Plus Black","999$","",R.drawable.phone_iphone7_plus_black,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Plus Gold","950$","",R.drawable.phone_iphone7_plus_gold,true));
        gridv_arrayList_model.add(new ProductInformationModel("Iphone 7 Plus Red","1050$","",R.drawable.phone_iphone7_plus_red,true));
    }

    private void prepareDataforSpinner(){

        list_tieu_chi.add("Giá từ cao đến thấp");
        list_tieu_chi.add("Giá từ thấp đến cao");
        list_tieu_chi.add("Lượt yêu thích");
        list_tieu_chi.add("100k - 500k");
        list_tieu_chi.add("500k - 2 triệu");
        list_tieu_chi.add("2 triệu - 5 triệu");
        list_tieu_chi.add("5 triệu - 10 triệu");
        list_tieu_chi.add("10 triệu - 30 triệu");
        list_tieu_chi.add("Hơn 30 triệu");

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            changes(ListOfProduct.this,SecondMainAcitivty.class);
            finish();
        }
        return super.onKeyDown(keyCode, event);

    }
}
