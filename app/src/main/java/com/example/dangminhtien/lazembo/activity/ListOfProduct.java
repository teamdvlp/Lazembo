package com.example.dangminhtien.lazembo.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dangminhtien.lazembo.Model.ProductInformationModel;
import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.adapter.ExpandableListViewAdapter;
import com.example.dangminhtien.lazembo.adapter.GridviewAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class ListOfProduct extends AppCompatActivity{
    TextView txt_type_of_goods;

    ArrayList<ProductInformationModel> gridv_arrayList_model;
    GridView gridv_list_goods;
    GridviewAdapter gridv_adapter;

    Spinner sp_tieu_chi,sp_price;
    ArrayList<String> list_tieu_chi,list_price;
    ArrayAdapter<String> adapter_tieu_chi,adapter_price;

    ImageView img_introView;
    Toolbar toolbar;
    DrawerLayout drawer_container;
    NavigationView nav_list_goods;
    ExpandableListView explv_list;
    ExpandableListViewAdapter explv_adapter;
    ArrayList<String> list_group_title;
    HashMap<String, ArrayList<String>>  list_sub_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_product);
        overridePendingTransition(R.anim.appear,R.anim.disappear);

        addsControls();
        addEvents();

        Bundle bundle = getIntent().getExtras();
        String loaiMatHang = (String) bundle.get("LoaiMatHang");
        txt_type_of_goods.setText(loaiMatHang);

    }
    private void addsControls() {
        //GHI NHỚ NHÉ
        addControlsFromMainActivity();

        txt_type_of_goods = (TextView) findViewById(R.id.txt_type_of_goods);

        gridv_arrayList_model = new ArrayList<>();
        this.prepareDatafor_Gridview();
        gridv_list_goods = (GridView) findViewById(R.id.gridv_list_goods);
        gridv_adapter = new GridviewAdapter(gridv_arrayList_model,this);
        gridv_list_goods.setAdapter(gridv_adapter);

        list_price = new ArrayList<>();
        list_tieu_chi = new ArrayList<>();
        this.prepareDatafor_Spinner();

        sp_price = (Spinner) findViewById(R.id.sp_price);
        adapter_price = new ArrayAdapter<>(ListOfProduct.this,android.R.layout.simple_spinner_dropdown_item,list_price);
        sp_price.setAdapter(adapter_price);

        sp_tieu_chi = (Spinner) findViewById(R.id.sp_tieu_chi);
        adapter_tieu_chi = new ArrayAdapter<>(ListOfProduct.this,android.R.layout.simple_spinner_dropdown_item,list_tieu_chi);
        sp_tieu_chi.setAdapter(adapter_tieu_chi);
    }

    private void addEvents() {
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

    protected void changes(Context context, Class classes){
        startActivity(new Intent(context,classes));
    }

    private void prepareDatafor_Gridview(){
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

    private void prepareDatafor_Spinner(){

        list_tieu_chi.add("Giá từ cao đến thấp");
        list_tieu_chi.add("Giá từ thấp đến cao");
        list_tieu_chi.add("Lượt yêu thích");
        list_price.add("100k - 500k");
        list_price.add("500k - 2 triệu");
        list_price.add("2 triệu - 5 triệu");
        list_price.add("5 triệu - 10 triệu");
        list_price.add("10 triệu - 30 triệu");
        list_price.add("Hơn 30 triệu");

    }
    //GHI NHỚ NHÉ
    private void addControlsFromMainActivity(){
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        drawer_container = (DrawerLayout) findViewById(R.id.drawer_container_lop);
        nav_list_goods = (NavigationView) findViewById(R.id.nav_list_goods_lop);
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
