package com.example.dangminhtien.lazembo.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.example.dangminhtien.lazembo.activity.*;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.TintContextWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.adapter.adapter_product;
import com.example.dangminhtien.lazembo.adapter.adapter_sp_account;
import com.example.dangminhtien.lazembo.data.Khachhang;
import com.example.dangminhtien.lazembo.data.Sanpham;
import com.example.dangminhtien.lazembo.data.get_set_Khachhang;
import com.example.dangminhtien.lazembo.data.get_set_sanpham;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by phamf on 04-Jun-17.
 */

public class Account_Fragment extends Fragment {
    private View view;
    private ArrayList<String> urls;
    // 0: not refresh 1: refresh
    public static int refresh = 0;
    private ArrayList<String> giasp;
    private ArrayList<String> tensp;
    private String ten_khachhang;
    private String sdt_khachhang;
    private String email_khachhang;
    private FirebaseAuth firebaseAuth;
    private TextView txt_ten_account, txt_email_account, txt_sdt_account, txt_hidden_account;
    private Button btn_sign_out_account, btn_sign_in_account;
    private ImageButton btn_add_account;
    private ProgressBar pb_account;
    private ConstraintLayout layout_parent_account;
    private ArrayList<Sanpham> sanphams;
    private Khachhang khachhang;
    private Sanpham sanpham;
    private ArrayList<String> paths;
    private RecyclerView recycle_sp_account;
    private adapter_product adapter_product;
    private static int Continue = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        view = inflater.inflate(R.layout.fragment_account,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addControls();
        addEvents();
        get_data();
    }
        private void get_data() {
        if (firebaseAuth.getCurrentUser() != null) {
            visible_layout_when_not_sign_in(true);
//            pb_account.setVisibility(View.VISIBLE);
            get_khachhang_from_firebase();
            get_sanpham_from_firebase();
        } else {
            visible_layout_when_not_sign_in(false);
        }
        }

    @Override
    public void onResume() {
        super.onResume();
        if(refresh == 1) {
           get_data();
           refresh = 0;
        }
    }

    private void addEvents() {
        btn_sign_in_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), activity_Login_Register.class));
            }
        });
        btn_sign_out_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb_account.setVisibility(View.VISIBLE);
                firebaseAuth.signOut();
                pb_account.setVisibility(View.GONE);
                get_data();
            }
        });

        btn_add_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firebaseAuth.getCurrentUser() != null) {
                    Intent intent = new Intent(getActivity(), activity_product.class);
                    intent.putExtra("uid", firebaseAuth.getCurrentUser().getUid());
                startActivity(intent);
            }

            }
        });
    }

    private void visible_layout_when_not_sign_in (boolean invisible) {
        if (invisible) {
            txt_hidden_account.setVisibility(View.GONE);
            btn_sign_in_account.setVisibility(View.GONE);
            pb_account.setVisibility(View.VISIBLE);
            layout_parent_account.setVisibility(View.VISIBLE);

        } else {
            txt_hidden_account.setVisibility(View.VISIBLE);
            btn_sign_in_account.setVisibility(View.VISIBLE);
            layout_parent_account.setVisibility(View.INVISIBLE);
        }
    }

    private void addControls() {
        txt_email_account = (TextView) view.findViewById(R.id.txt_email_account);
        txt_sdt_account = (TextView) view.findViewById(R.id.txt_sdt_account);
        txt_ten_account = (TextView) view.findViewById(R.id.txt_ten_account);
        btn_sign_out_account = (Button) view.findViewById(R.id.btn_sign_out_account);
        layout_parent_account = (ConstraintLayout) view.findViewById(R.id.layout_parent_account);
        txt_hidden_account = (TextView) view.findViewById(R.id.txt_hidden_account);
        btn_sign_in_account = (Button) view.findViewById(R.id.btn_sign_in_account);
        pb_account = (ProgressBar) view.findViewById(R.id.pb_account);
        btn_add_account = (ImageButton) view.findViewById(R.id.btn_add_account);
        recycle_sp_account = (RecyclerView) view.findViewById(R.id.recycle_sp_account);

    }

    private void add_adapter_to_recycle_sp_account() {
        HashMap hashMap = Account_Fragment.this.khachhang.getSanphams();
        Set set = hashMap.entrySet();
        Iterator iterator = set.iterator();
        ArrayList<String> maps = new ArrayList<>();
        get_set_sanpham get_set_sanpham = new get_set_sanpham(getContext());
        while (iterator.hasNext()) {
            Map.Entry<String,String> entry = (Map.Entry<String, String>) iterator.next();
                if (entry.getValue() != "" && iterator.hasNext()) {
            maps.add(entry.getValue());
                }
        }
            get_set_sanpham.get_sanphams(maps);
            get_set_sanpham.on_getsanphams(new get_set_sanpham.get_sanphams() {
                @Override
                public void on_get_sanphams(ArrayList<Sanpham> sanphams) {
                    adapter_sp_account recyclerViewAdapter = new adapter_sp_account(getContext(), sanphams);
//                    Toast.makeText(getContext(), recyclerViewAdapter.getItemCount() + "", Toast.LENGTH_SHORT).show();
                    if (sanphams.size() != 0 && sanphams != null) {
                        if(Continue == 0) {
                    recycle_sp_account.setAdapter(recyclerViewAdapter);
                        Continue = 1;} else {
                            recyclerViewAdapter.notifyDataSetChanged();
                        }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    recycle_sp_account.setLayoutManager(linearLayoutManager);
//                    pb_account.setVisibility(View.INVISIBLE);
                }}
            });

    }

    public static Account_Fragment newInstance() {
        Account_Fragment fragment = new Account_Fragment();
        return fragment;
    }

    public void get_khachhang_from_firebase() {
        pb_account.setVisibility(View.VISIBLE);
        get_set_Khachhang get_set_khachhang = new get_set_Khachhang(getContext());
        get_set_khachhang.get_khachhang(firebaseAuth.getCurrentUser().getUid());
        get_set_khachhang.set_on_get_khachhang(new get_set_Khachhang.get_khachhang() {
            @Override
            public void on_get_khachhang(Khachhang khachhang) {
                Account_Fragment.this.khachhang = khachhang;
                txt_ten_account.setText(khachhang.getHOVATEN());
                txt_sdt_account.setText(khachhang.getSdt());
                txt_email_account.setText(khachhang.getEmail());
                add_adapter_to_recycle_sp_account();
            }
        });
    }

    public void get_sanpham_from_firebase() {
        get_set_sanpham get_set_sanpham = new get_set_sanpham(getContext());
    }
}
