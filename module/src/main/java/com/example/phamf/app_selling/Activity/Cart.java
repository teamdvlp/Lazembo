package com.example.phamf.app_selling.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.example.phamf.app_selling.Adapters.RecyclerViewAdapter;
import com.example.phamf.app_selling.Model.CartModel;
import com.example.phamf.app_selling.R;

import java.util.ArrayList;

/**
 * Created by phamf on 30-May-17.
 */

public class Cart extends AppCompatActivity {
    Button btn_pay;
    TextView txt_total_money;

    RecyclerView rcv_cart;
    ArrayList<CartModel> list;
    RecyclerViewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cart);
        addControls();
        addEvents();
    }

    private void addEvents() {

    }

    private void addControls() {
        btn_pay = (Button) findViewById(R.id.btn_pay);
        txt_total_money = (TextView) findViewById(R.id.txt_total_money);

        list = new ArrayList<>();
        prepareDataForRecyclerView();
        rcv_cart = (RecyclerView) findViewById(R.id.rcv_cart);
        adapter = new RecyclerViewAdapter(this,list,rcv_cart);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcv_cart.setLayoutManager(linearLayoutManager);
        rcv_cart.setAdapter(adapter);
    }

    private void prepareDataForRecyclerView() {
        list.add(new CartModel(R.drawable.phone_iphone7_black,"Iphone 7 Black","15000000",2));
        list.add(new CartModel(R.drawable.earphone_airpods,"Earphone","3000000",4));
        list.add(new CartModel(R.drawable.pin_adata_pt100_10000,"Pin","600000",5));
        list.add(new CartModel(R.drawable.phone_iphone7_black,"Iphone 7 Black","15000000",2));
        list.add(new CartModel(R.drawable.earphone_airpods,"Earphone","3000000",4));
        list.add(new CartModel(R.drawable.pin_adata_pt100_10000,"Pin","600000",5));
        list.add(new CartModel(R.drawable.phone_iphone7_black,"Iphone 7 Black","15000000",5));
        list.add(new CartModel(R.drawable.earphone_airpods,"Earphone","3000000",1));
        list.add(new CartModel(R.drawable.pin_adata_pt100_10000,"Pin","600000",7));
    }
}
