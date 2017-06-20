package com.example.dangminhtien.lazembo.Fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import com.example.dangminhtien.lazembo.Model.xu_ly_bottom_sheet;
import com.example.dangminhtien.lazembo.activity.*;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.TintContextWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.adapter.adapter_product;
import com.example.dangminhtien.lazembo.adapter.adapter_sp_account;
import com.example.dangminhtien.lazembo.data.Khachhang;
import com.example.dangminhtien.lazembo.data.Sanpham;
import com.example.dangminhtien.lazembo.data.get_set_Khachhang;
import com.example.dangminhtien.lazembo.data.get_set_sanpham;
import com.example.dangminhtien.lazembo.helper.helper;
import com.example.dangminhtien.lazembo.helper.helper_account_fragment;
import com.example.dangminhtien.lazembo.helper.helper_phanloai_sp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Account_Fragment extends Fragment implements RecyclerView.OnItemTouchListener {
    private View view;
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
    private get_set_sanpham get_set_sanpham;
    private get_set_Khachhang get_set_khachhang;
    private helper_account_fragment helper_account_fragment;
    // true: chưa set adapter, false: đã set adapter
    private static boolean is_setAdapter = false;
    private adapter_sp_account recyclerViewAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        view = inflater.inflate(R.layout.fragment_account,container,false);
        get_set_khachhang = new get_set_Khachhang(getContext());
        get_set_sanpham = new get_set_sanpham(getContext());
        helper_account_fragment = new helper_account_fragment(getContext());
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
            show_progressbar(true);
            get_khachhang_from_firebase();
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
                startActivity(new Intent(getActivity(), activity_login.class));
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
            btn_sign_in_account.setVisibility(View.GONE);
            txt_hidden_account.setVisibility(View.GONE);
        } else {
            for (int i = 0; i < layout_parent_account.getChildCount(); i++) {
                View view = layout_parent_account.getChildAt(i);
                view.setVisibility(View.GONE);
            }
            btn_sign_in_account.setVisibility(View.VISIBLE);
            txt_hidden_account.setVisibility(View.VISIBLE);
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
        ArrayList<String> masp = helper_account_fragment.get_masp(khachhang);
        get_set_sanpham.get_sanphams(masp);
        show_progressbar(false);
        get_set_sanpham.set_on_get_sanphams_listener(new get_set_sanpham.get_sanphams() {
            @Override
            public void on_get_sanphams(final ArrayList<Sanpham> sanphams) {
                show_progressbar(true);
                Account_Fragment.this.sanphams =  sanphams;
                if (sanphams.size() != 00 && sanphams != null ) {
                    get_set_sanpham.getImages(helper_account_fragment.get_paths(sanphams));
                    get_set_sanpham.set_on_get_images_listener(new get_set_sanpham.get_images() {
                        @Override
                        public void on_get_images(ArrayList<Bitmap> bitmaps) {
                            set_adapter(bitmaps);
                        }
                    });
                }
            }
            });
    }
        public void set_adapter(ArrayList<Bitmap> bitmaps) {
            if (!is_setAdapter) {
                recyclerViewAdapter = new adapter_sp_account(getContext(), sanphams, bitmaps, recycle_sp_account);
                recycle_sp_account.setAdapter(recyclerViewAdapter);
                is_setAdapter = true;
            } else {
                recyclerViewAdapter.notifyDataSetChanged();
            }

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            recycle_sp_account.setLayoutManager(linearLayoutManager);
            recycle_sp_account.setOnTouchListener(new View.OnTouchListener() {
                @TargetApi(Build.VERSION_CODES.N)
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            xu_ly_bottom_sheet.bottomSheetBehavior.setPeekHeight(new helper().convert_dp_to_px(getResources(), 450));
                            break;
                        case MotionEvent.ACTION_UP:
                            xu_ly_bottom_sheet.bottomSheetBehavior.setPeekHeight(new helper().convert_dp_to_px(getResources(), 40));
                            xu_ly_bottom_sheet.bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                            break;
                        case MotionEvent.ACTION_MOVE:
                            xu_ly_bottom_sheet.bottomSheetBehavior.setPeekHeight(new helper().convert_dp_to_px(getResources(), 450));
                            break;
                        case MotionEvent.ACTION_SCROLL:
                            xu_ly_bottom_sheet.bottomSheetBehavior.setPeekHeight(new helper().convert_dp_to_px(getResources(), 450));
                            break;
                    }
                    return false;
                }
            });
            show_progressbar(false);
        }

    public void show_progressbar (boolean visible) {
            if (visible) {
                for (int i = 0; i < layout_parent_account.getChildCount(); i++) {
                    View view = layout_parent_account.getChildAt(i);

                        view.setVisibility(View.GONE);
                }
                pb_account.setVisibility(View.VISIBLE);
            } else {
                    for (int i = 0; i < layout_parent_account.getChildCount(); i++) {
                        View view = layout_parent_account.getChildAt(i);

                        view.setVisibility(View.VISIBLE);
                    }
                    pb_account.setVisibility(View.GONE);

    }
        visible_layout_when_not_sign_in(true);

    }

    public static Account_Fragment newInstance() {
        Account_Fragment fragment = new Account_Fragment();
        return fragment;
    }

    public void get_khachhang_from_firebase() {
                    get_set_khachhang.get_khachhang(firebaseAuth.getCurrentUser().getUid());
                    get_set_khachhang.set_on_get_khachhang_listener(new get_set_Khachhang.get_khachhang() {
                        @Override
                        public void on_get_khachhang(Khachhang khachhang) {
                            Account_Fragment.this.khachhang = khachhang;
                            txt_ten_account.setText(khachhang.getHOVATEN());
                            txt_sdt_account.setText(khachhang.getSdt());
                            txt_email_account.setText(khachhang.getEmail());
                                // reset recycle view
                            if (recyclerViewAdapter != null) {
                                sanphams.clear();
                                recyclerViewAdapter.notifyDataSetChanged();
                            }
                            add_adapter_to_recycle_sp_account();

            }
        });
    }


    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
