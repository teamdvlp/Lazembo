package com.example.dangminhtien.lazembo.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.data.Danhmucsp;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Main_Lazembo extends AppCompatActivity implements Danhmucsp.datachanged{
    final static int RESULT_GALLARY = 69;
    private Danhmucsp danhmucsp;
    private EditText txtTensp, txtGia, txtMota;
    private Spinner sp1st, sp2nd, sp3th, sp4th;
    private Button btnGallary;
    private ImageView imgSp1,imgSp2,imgSp3,imgSp4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        setContentView(R.layout.activity_main__lazembo);
        addControls();
        addEvents();
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {

            }
        };
            }


    private void addEvents() {
        final String[] cache = new String[4];
        final ArrayList arrayList = new ArrayList();
        sp1st.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Danhmucsp danhmucsp = new Danhmucsp(Main_Lazembo.this, sp2nd);
                sp2nd.setAdapter(new ArrayAdapter<String>(Main_Lazembo.this,android.R.layout.simple_spinner_item,arrayList));
                sp3th.setAdapter(new ArrayAdapter<String>(Main_Lazembo.this,android.R.layout.simple_spinner_item,arrayList));
                sp4th.setAdapter(new ArrayAdapter<String>(Main_Lazembo.this,android.R.layout.simple_spinner_item,arrayList));
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
                Danhmucsp danhmucsp = new Danhmucsp(Main_Lazembo.this, sp3th);
                sp3th.setAdapter(new ArrayAdapter<String>(Main_Lazembo.this,android.R.layout.simple_spinner_item,arrayList));
                sp4th.setAdapter(new ArrayAdapter<String>(Main_Lazembo.this,android.R.layout.simple_spinner_item,arrayList));
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
                Danhmucsp danhmucsp = new Danhmucsp(Main_Lazembo.this, sp4th);
                sp4th.setAdapter(new ArrayAdapter<String>(Main_Lazembo.this,android.R.layout.simple_spinner_item,arrayList));
                cache[2] = parent.getSelectedItem().toString();
                danhmucsp.getChild(new String[]{cache[0],cache[1],cache[2]});


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnGallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_GALLARY);
            }
        });
    }

    private void addControls() {
        txtGia = (EditText) findViewById(R.id.txtGia);
        txtMota = (EditText) findViewById(R.id.txtMota);
        txtTensp = (EditText) findViewById(R.id.txtTensp);
        sp1st = (Spinner) findViewById(R.id.sp1st);
        sp2nd = (Spinner) findViewById(R.id.sp2nd);
        sp3th = (Spinner) findViewById(R.id.sp3th);
        sp4th = (Spinner) findViewById(R.id.sp4th);
        btnGallary = (Button) findViewById(R.id.btnGallary);
        imgSp1 = (ImageView) findViewById(R.id.imgSp1);
        imgSp2 = (ImageView) findViewById(R.id.imgSp2);
        imgSp3 = (ImageView) findViewById(R.id.imgSp3);
        imgSp4 = (ImageView) findViewById(R.id.imgSp4);
        Button btnDang = (Button) findViewById(R.id.btnDang);

        danhmucsp = new Danhmucsp(Main_Lazembo.this, sp1st);
        danhmucsp.getChild(new String[]{});
    }

    private void xulySp(ArrayList<String> arrayList, Context context, Spinner sp) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Main_Lazembo.this,android.R.layout.simple_spinner_item,arrayList);
        sp.setAdapter(arrayAdapter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK & null != data & requestCode == RESULT_GALLARY) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                byte[] b = new byte[2048];
                int i = -1;
                while ((i = imageStream.read()) != -1) {

                }
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                if ( null == imgSp1.getDrawable()) {
                    imgSp1.setImageBitmap(selectedImage);
                } else if (null == imgSp2.getDrawable() ) {
                    imgSp2.setImageBitmap(selectedImage);
                } else if (null == imgSp3.getDrawable()) {
                    imgSp3.setImageBitmap(selectedImage);
                } else if (null == imgSp4.getDrawable()) {
                    imgSp4.setImageBitmap(selectedImage);
                }
        } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }}

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void onDatachanged(ArrayList<String> arr, Context context, Spinner sp) {
        xulySp(arr, context, sp);
    }
}
