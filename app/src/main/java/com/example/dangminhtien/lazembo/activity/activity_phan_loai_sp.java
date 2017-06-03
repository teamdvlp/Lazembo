package com.example.dangminhtien.lazembo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.data.Danhmucsp;
import com.example.dangminhtien.lazembo.data.Sanpham;
import com.example.dangminhtien.lazembo.data.get_set_sanpham;

import java.util.ArrayList;

public class activity_phan_loai_sp extends AppCompatActivity implements Danhmucsp.datachanged {
    final static int RESULT_GALLARY = 69;
    final static String[] cache = new String[6];
    static String path_spinner;
    static int a = 0;
    private Danhmucsp danhmucsp;
    private Spinner sp1st, sp2nd, sp3th, sp4th;
    private ImageButton btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phan_loai_sp);
        addControls();
        addEvents();
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {

            }
        };
            }


    private void addEvents() {
        final ArrayList arrayList = new ArrayList();
        sp1st.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Danhmucsp danhmucsp = new Danhmucsp(activity_phan_loai_sp.this, sp2nd);
                sp2nd.setAdapter(new ArrayAdapter<String>(activity_phan_loai_sp.this, android.R.layout.simple_spinner_item, arrayList));
                sp3th.setAdapter(new ArrayAdapter<String>(activity_phan_loai_sp.this, android.R.layout.simple_spinner_item, arrayList));
                sp4th.setAdapter(new ArrayAdapter<String>(activity_phan_loai_sp.this, android.R.layout.simple_spinner_item, arrayList));
                cache[0] = parent.getSelectedItem().toString();
                danhmucsp.getChild(new String[]{cache[0]});
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp2nd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Danhmucsp danhmucsp = new Danhmucsp(activity_phan_loai_sp.this, sp3th);
                sp3th.setAdapter(new ArrayAdapter<String>(activity_phan_loai_sp.this, android.R.layout.simple_spinner_item, arrayList));
                sp4th.setAdapter(new ArrayAdapter<String>(activity_phan_loai_sp.this, android.R.layout.simple_spinner_item, arrayList));
                cache[1] = parent.getSelectedItem().toString();
                danhmucsp.getChild(new String[]{cache[0],cache[1]});
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp3th.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Danhmucsp danhmucsp = new Danhmucsp(activity_phan_loai_sp.this, sp4th);
                sp4th.setAdapter(new ArrayAdapter<String>(activity_phan_loai_sp.this, android.R.layout.simple_spinner_item, arrayList));
                cache[2] = parent.getSelectedItem().toString();
                danhmucsp.getChild(new String[]{cache[0],cache[1],cache[2]});
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp4th.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (!parent.getSelectedItem().toString().equals("")) {
                cache[3] = parent.getSelectedItem().toString();
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sanpham sanpham = Sanpham.getInstance();
                for (int i = 0; i < cache.length; i++) {
                    if (cache[i] == null) {
                        cache[i] = "Sản phẩm";
                        cache[i + 1] = sanpham.getIdsp();
                        break;
                    }
                }

                for (int i = 0; i < cache.length; i++) {
                    Toast.makeText(getApplicationContext(), cache[i], Toast.LENGTH_SHORT).show();
                }
                get_set_sanpham get_set_sanpham = new get_set_sanpham(activity_phan_loai_sp.this);
                get_set_sanpham.upLoadSanpham(sanpham, sanpham.getIdsp());
                get_set_sanpham.write_path_by_path(sanpham.getIdsp(), cache);
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

    @Override
    public void onDatachanged(ArrayList<String> arr, Context context, Spinner sp) {
        xulySp(arr, context, sp);
    }
}
