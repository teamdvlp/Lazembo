package com.example.dangminhtien.lazembo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.data.Danhmucsp;
import com.example.dangminhtien.lazembo.data.Sanpham;
import com.example.dangminhtien.lazembo.data.get_set_Khachhang;
import com.example.dangminhtien.lazembo.data.get_set_sanpham;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class activity_phan_loai_sp extends AppCompatActivity implements Danhmucsp.datachanged {
    final static String[] cache = new String[6];
    private Danhmucsp danhmucsp;
    private Spinner sp1st, sp2nd, sp3th, sp4th;
    private ImageButton btn_submit;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phan_loai_sp);
        firebaseAuth = FirebaseAuth.getInstance();
        addControls();
        addEvents();
    }

    private void addEvents() {
        process_spinner();

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_path_up_to_firebase();
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void get_path_up_to_firebase() {
        Sanpham sanpham = Sanpham.getInstance();
        // vì sản phầm được ghi trong mục idsp nằm bên trong mục sản phẩm nên cần phải add vô thêm
        int count = 0;
        for (int i = 0; i < cache.length; i++) {
            if (null == cache[i]) {
                cache[i] = "Sản phẩm";
                cache[i + 1] = sanpham.getIdsp();
                count = i + 1;
                break;
            }
        }

        get_set_sanpham get_set_sanpham = new get_set_sanpham(activity_phan_loai_sp.this);
        get_set_sanpham.upLoadSanpham(sanpham, sanpham.getIdsp());
        get_set_sanpham.write_path_by_path(sanpham.getIdsp(), cache, count);
        get_set_Khachhang get_set_khachhang = new get_set_Khachhang(getApplicationContext());
        get_set_khachhang.up_sp_to_khachhang(sanpham.getIdsp(),firebaseAuth.getCurrentUser().getUid());

    }

    private void process_spinner() {
        final ArrayList no_item = new ArrayList();
        final ArrayAdapter delete_item = new ArrayAdapter<String>(activity_phan_loai_sp.this, android.R.layout.simple_spinner_item, no_item);
        sp1st.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Danhmucsp danhmucsp = new Danhmucsp(activity_phan_loai_sp.this, sp2nd);
                // Xoá item trong các spinner bên dưới
                sp2nd.setAdapter(delete_item);
                sp3th.setAdapter(delete_item);
                sp4th.setAdapter(delete_item);
                // Lấy ra phần tử đang được chọn nhằm mục đích lấy các child bên trong nó trong firebase để set cho spinner bên dưới
                cache[0] = parent.getSelectedItem().toString();
                // Lấy đồng thời set dữ liệu cho spinner bên dưới
                danhmucsp.getChild(new String[]{cache[0]});
                cache[1] = null;
                cache[2] = null;
                cache[3] = null;
                cache[4] = null;
                cache[5] = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp2nd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Danhmucsp danhmucsp = new Danhmucsp(activity_phan_loai_sp.this, sp3th);
                sp3th.setAdapter(delete_item);
                sp4th.setAdapter(delete_item);
                cache[1] = parent.getSelectedItem().toString();
                danhmucsp.getChild(new String[]{cache[0],cache[1]});
                cache[2] = null;
                cache[3] = null;
                cache[4] = null;
                cache[5] = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp3th.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Danhmucsp danhmucsp = new Danhmucsp(activity_phan_loai_sp.this, sp4th);
                sp4th.setAdapter(delete_item);
                cache[2] = parent.getSelectedItem().toString();
                danhmucsp.getChild(new String[]{cache[0],cache[1],cache[2]});
                cache[3] = null;
                cache[4] = null;
                cache[5] = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp4th.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cache[3] = parent.getSelectedItem().toString();
                cache[4] = null;
                cache[5] = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void addControls() {
        sp1st = (Spinner) findViewById(R.id.sp1st);
        sp2nd = (Spinner) findViewById(R.id.sp2nd);
        sp3th = (Spinner) findViewById(R.id.sp3th);
        sp4th = (Spinner) findViewById(R.id.sp4th);
        btn_submit = (ImageButton) findViewById(R.id.btn_submit);
        danhmucsp = new Danhmucsp(activity_phan_loai_sp.this, sp1st);
        danhmucsp.getChild(new String[]{});
    }

    private void xulySp(ArrayList<String> arrayList, Context context, Spinner sp) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(activity_phan_loai_sp.this, android.R.layout.simple_spinner_item, arrayList);
        sp.setAdapter(arrayAdapter);
    }

    // bắt sự kiện khi lấy xong dữ liệu
    @Override
    public void onDatachanged(ArrayList<String> arr, Context context, Spinner sp) {
        xulySp(arr, context, sp);
    }
}
