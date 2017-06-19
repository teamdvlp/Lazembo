package com.example.dangminhtien.lazembo.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.adapter.AdapterHinhSp;
import com.example.dangminhtien.lazembo.data.Sanpham;
import com.example.dangminhtien.lazembo.data.get_set_sanpham;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Iterator;

public class fragment_product_customer extends Fragment {
    private static final String KEY_AUTHENTICATION = "authentication";
    private final static int RESULT_GALLARY = 69;
    public static ArrayList<Bitmap> bitmaps_hinh_sp;
    private static int FLAG_DIALOG = 1;
    private EditText txt_gia, txt_giap_truoc_khi_giam, txt_size_color, txt_ten_sp;
    private ImageButton btn_browse_gallary, btn_submit;
    private Button btn_submit_dialog;
    private ViewPager pager_hinh_sp;
    private TextView txt_camket, txt_hinhthuc_thanhtoan;
    private RatingBar rb_rating;
    private AlertDialog alert_dialog;
    private Spinner sp_mausac, sp_kichthuoc;
    private ArrayList<String> source_mausac;
    private ArrayList<String> source_kichthuoc;
    private ArrayList<String> path_hinh_sp;
    // 0: color, 1: size
    private ArrayAdapter adapter_sp_kichthuoc;
    private ArrayAdapter adapter_sp_mausac;
    private AdapterHinhSp adapter_hinh_sp;
    private FirebaseAuth firebaseAuth;
    // 1: Người bán 0: Người mua
    private int AUTHENTICATION;
    private get_set_sanpham get_set_sanpham;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public fragment_product_customer() {
    }

    public static fragment_product_customer newInstance(String param1, String param2) {
        fragment_product_customer fragment = new fragment_product_customer();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_product_customer, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void get_sanphams () {
        get_set_sanpham = new get_set_sanpham(getContext());
        get_set_sanpham.get_all_ma_sanpham();
        get_set_sanpham.set_on_get_all_ma_sanpham(new get_set_sanpham.get_all_ma_sanpham() {
            @Override
            public void on_get_all_ma_sanpham(ArrayList<String> ma_sanpham_storage) {
                get_set_sanpham.get_sanphams(ma_sanpham_storage);
                get_set_sanpham.set_on_get_sanphams_listener(new get_set_sanpham.get_sanphams() {
                    @Override
                    public void on_get_sanphams(ArrayList<Sanpham> sanphams) {
                        Iterator<Sanpham> sanphamIterator = sanphams.iterator();
                        while (sanphamIterator.hasNext()) {
                        Toast.makeText(getContext(), sanphamIterator.next().getTensp(), Toast.LENGTH_SHORT).show();
                    }}
                });
            }
        });
    }

}
