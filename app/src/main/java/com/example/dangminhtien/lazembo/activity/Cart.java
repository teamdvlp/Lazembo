package com.example.dangminhtien.lazembo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.adapter.RecyclerViewAdapter;
import com.example.dangminhtien.lazembo.data.Sanpham;
import com.example.dangminhtien.lazembo.data.get_set_sanpham;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    public Button btn_pay;
    public TextView txt_total_money;

    public RecyclerView rcv_cart;
    private ArrayList<Sanpham> list;
    public RecyclerViewAdapter adapter;
    //TẠO RA ANH GET_SET_SAN_PHAM
    private get_set_sanpham get_set_sanpham;

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
        list = new ArrayList<>();
        //NÀY CỦA MÀY, CMT LÀM ĐÉO ?
        get_set_sanpham = new get_set_sanpham(this);
        get_set_sanpham.get_all_ma_sanpham();
        get_set_sanpham.set_on_get_all_ma_sanpham(new get_set_sanpham.get_all_ma_sanpham() {
            @Override
            public void on_get_all_ma_sanpham(ArrayList<String> ma_sanpham_storage) {
                get_set_sanpham.get_sanphams(ma_sanpham_storage);
            }
        });

        get_set_sanpham.set_on_get_sanphams_listener(new get_set_sanpham.get_sanphams() {
            @Override
            public void on_get_sanphams(ArrayList<Sanpham> sanphams) {
                //GET XONG DỮ LIỆU
                list.addAll(sanphams);
            }
        });

        btn_pay = (Button) findViewById(R.id.btn_pay);
        txt_total_money = (TextView) findViewById(R.id.txt_total_money);

        rcv_cart = (RecyclerView) findViewById(R.id.rcv_cart);
        //SỬA CONSTRUCTOR CHÚT CHÚT, TRUYỀN get_set_sanpham dô để lấy list bitmap
        adapter = new RecyclerViewAdapter(this,list,rcv_cart,get_set_sanpham);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcv_cart.setLayoutManager(linearLayoutManager);
        rcv_cart.setAdapter(adapter);


    }



}
