package com.example.dangminhtien.lazembo.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AlignmentSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class fragment_layout_sp extends Fragment implements get_set_sanpham.notifiDataChange {
    private static final String KEY_AUTHENTICATION = "authentication";
    private final static int RESULT_GALLARY = 69;
    EditText txtGiasp, txtGiapSpTruongkhigiam, txtSizeColor, txtTensp;
    TextView txtCamket, txtHinhthucthanhtoan;
    RatingBar rbRatingsp;
    static ArrayList<Bitmap> bitmaps;
    ViewPager pagerHinhsp;
    AlertDialog alertDialog;
    Button btn_insert_picture, btnXacnhan_dialog;
            ImageButton btnXacnhanSeller;
    Spinner spMausac, spKichco;
    ArrayList<String> SourceMausac;
    ArrayList<String> SourceKichco;
    ArrayList<String> pathHinh;
    private static int size = 0;

    // 0: color, 1: size
    private static int FLAG_DIALOG = 1;
    ArrayList<String> hinhsp;
    private ArrayAdapter AdapterSpKichco;
    private ArrayAdapter AdapterSpMausac;
    private AdapterHinhSp adapterHinhSp;
    // 1: người bán
    // 0: người mua
    private int AUTHENTICATION;

//    private OnFragmentInteractionListener mListener;

    public fragment_layout_sp() {



    }

    public static fragment_layout_sp newInstance(int AUTHENTICATION) {
        fragment_layout_sp fragment = new fragment_layout_sp();
        Bundle args = new Bundle();
        args.putInt(KEY_AUTHENTICATION, AUTHENTICATION);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            AUTHENTICATION = getArguments().getInt(KEY_AUTHENTICATION);
        }
    }

    public void createDialog () {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_size_color, null);
        btnXacnhan_dialog = (Button) view.findViewById(R.id.btnXacnhan_dialog);
        txtSizeColor = (EditText) view.findViewById(R.id.txtSizeColor);
        builder.setView(view);
        alertDialog = builder.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtCamket = (TextView) view.findViewById(R.id.txtCamket);
        txtGiapSpTruongkhigiam = (EditText) view.findViewById(R.id.txtGiaspTruockhigiam);
        txtGiasp = (EditText) view.findViewById(R.id.txtGiasp);
        spKichco = (Spinner) view.findViewById(R.id.spKichco);
        spMausac = (Spinner) view.findViewById(R.id.spMausac);
        rbRatingsp = (RatingBar) view.findViewById(R.id.rbRatingsp);
        pagerHinhsp = (ViewPager) view.findViewById(R.id.pagerHinhsp);
        btn_insert_picture = (Button) view.findViewById(R.id.btn_insert_picture);
        SourceKichco = new ArrayList<>();
        SourceMausac = new ArrayList<>();
        txtTensp = (EditText) view.findViewById(R.id.txtTensp);
        bitmaps = new ArrayList<>();
        get_set_sanpham get_set_sanpham = new get_set_sanpham(getContext());
        try {
            get_set_sanpham.getImage("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        adapterHinhSp = new AdapterHinhSp(getActivity().getSupportFragmentManager(), getContext(), bitmaps);
        pagerHinhsp.setAdapter(adapterHinhSp);
        btnXacnhanSeller = (ImageButton) view.findViewById(R.id.btnXacnhanSeller);
        pathHinh = new ArrayList<>();
        createDialog();
        addEvents();
    }

    private void addEvents() {
        btn_insert_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyHienthiPagerHinhSp();
            }
        });

        btnXacnhanSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xacnhanUptoFirebase();
            }
        });

        btnXacnhan_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FLAG_DIALOG == 0) {
                    SourceMausac.addAll(getArrayDialog());
                    xulyHienthiSpMausac(SourceMausac);
                } else if (FLAG_DIALOG == 1) {
                    SourceKichco.addAll(getArrayDialog());
                    xulyHienthiSpKichco(SourceKichco);
                }
                alertDialog.dismiss();
            }
        });


        spKichco.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                alertDialog.show();
                FLAG_DIALOG = 1;
                return false;
            }
        });

        spKichco.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                FLAG_DIALOG = 1;
                alertDialog.show();
                return false;
            }
        });

        spMausac.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                FLAG_DIALOG = 0;
                alertDialog.show();
                return false;
            }
        });

    }

    private void xacnhanUptoFirebase() {
        if (checkPagerHinhsp() && checkSpMausac() && checkPagerHinhsp() && checkTxtGiasp() && checkTxtGiaspTruocKhiGiam() && checkTxtTensp()) {
            uptoFirebase();
        }
    }

    private boolean checkTxtTensp () {
        if (!txtTensp.getText().toString().equals("") && null != txtTensp.toString()) {
            return true;
        } else {
            txtTensp.requestFocus();
            return false;
        }
    }

    private void uptoFirebase() {
        get_set_sanpham uploadSanpham = new get_set_sanpham(getContext());
        UUID uuid = new UUID(100000,1);
        String masp = uuid.toString();
        String path_image = uploadSanpham.upLoadImage(bitmaps.get(0), "Sản phẩm/" + getDateAndTime());
        pathHinh.add(path_image);
        Sanpham sanpham = new Sanpham("hahaha", SourceKichco, SourceMausac, rbRatingsp.getRating(), Long.valueOf(txtGiasp.getText().toString()), "San pham xam nach", pathHinh,Float.valueOf(txtGiapSpTruongkhigiam.getText().toString()), masp);
        uploadSanpham.upLoadSanpham(sanpham, masp);
    }

    private ArrayList<String> getArrayDialog () {
        String[] size_color = txtSizeColor.getText().toString().split("\n");
        ArrayList<String> size_colors = new ArrayList<>();
        size_colors.addAll(Arrays.asList(size_color));
        return size_colors;
    }

    private boolean checkTxtGiasp () {

        if (!txtGiasp.getText().toString().equals("") | null == txtGiasp.toString()) {
            return true;
        } else {
            txtGiasp.requestFocus();
            return false;
        }
    }

    private String getDateAndTime () {
        Calendar calendar = Calendar.getInstance();
        String dateFormat = "ddmmyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        Date date = calendar.getTime();
        return simpleDateFormat.format(date) + date.getTime();
    }

    private boolean checkTxtGiaspTruocKhiGiam () {

        if (!txtGiapSpTruongkhigiam.getText().toString().equals("") | null == txtGiapSpTruongkhigiam.toString()) {
            return true;
        } else {
            txtGiapSpTruongkhigiam.requestFocus();
            return false;
        }

    }

    private void xulyHienthiTxtCamKet () {

    }

    private boolean checkSpMausac () {
        if (SourceMausac.isEmpty()) {
            spMausac.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean checkSpKichco () {
        if (SourceKichco.isEmpty() ){
            spKichco.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean checkPagerHinhsp () {
        if (SourceKichco.isEmpty()) {
            pagerHinhsp.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void xulyHienthiSpKichco (ArrayList<String> SourceKichco) {
        this.SourceKichco = new ArrayList<>();
        this.SourceKichco.addAll(SourceKichco);
        AdapterSpKichco = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, this.SourceKichco);
        spKichco.setAdapter(AdapterSpKichco);
    }

    private void xulyHienthiSpMausac (ArrayList<String> SourceMausac) {
        this.SourceMausac = new ArrayList<>();
        this.SourceMausac.addAll(SourceMausac);
        AdapterSpMausac = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, this.SourceMausac);
        spMausac.setAdapter(AdapterSpMausac);
    }

    private void xulyHienthiPagerHinhSp () {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, RESULT_GALLARY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK & null != data & requestCode == RESULT_GALLARY) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                bitmaps.add(selectedImage);
                size ++;
                adapterHinhSp.notifyDataSetChanged();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }}
    }

    private void setTextHinhthucgiaohang () {

        SpannableStringBuilder styledString
                = new SpannableStringBuilder(
                "Hình thức vận chuyển\n\n" // 22
                        + "COD: " // 5
                        + "Hình thức vận chuyển tận nhà. Do bưu điện Viettel vận chuyển, có tính phí vận chuyển đối với đơn hàng dưới 300 nghìn\n\n" // 118
                        + "VISA/MASTER CARD: " // 18
                        + "Miễn phí vận chuyển, Do bưu điện Viettel vận chuyển \n\n" // 53
                        + "Chuyển khoảng: " // 15
                        + "Miễn phí vận chuyển, Vietcombank: 19802948123, Ngân hàng quân đội: 12948192893, Ngân hàng Đông Á: 19382838192" // 109
        );

        styledString.setSpan(new RelativeSizeSpan(1.7f), 0, 22 - 1, 0);

        styledString.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, 22 - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        styledString.setSpan(new ForegroundColorSpan(Color.RED), 0, 22 - 1  ,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        styledString.setSpan(new ForegroundColorSpan(Color.BLUE), 22, 22 + 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        styledString.setSpan(new ForegroundColorSpan(Color.BLUE), 27 + 118, 27 + 118 + 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        styledString.setSpan(new ForegroundColorSpan(Color.BLUE), 27 + 118 + 18 + 53, 27 + 118 + 18 + 53 + 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    }


    @Override
    public void notifi(Bitmap bitmap) {

    }
}
