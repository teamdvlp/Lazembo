package com.example.dangminhtien.lazembo.Fragment;

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

import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.activity.activity_phan_loai_sp;
import com.example.dangminhtien.lazembo.adapter.AdapterHinhSp;
import com.example.dangminhtien.lazembo.data.Sanpham;
import com.example.dangminhtien.lazembo.data.get_set_sanpham;
import com.example.dangminhtien.lazembo.helper.helper;
import com.google.firebase.auth.FirebaseAuth;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;


public class fragment_product extends Fragment implements get_set_sanpham.get_sanpham {
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

    public fragment_product() {}

    public static fragment_product newInstance(int AUTHENTICATION) {
        fragment_product fragment = new fragment_product();
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
        btn_submit_dialog = (Button) view.findViewById(R.id.btn_submit_dialog);
        txt_size_color = (EditText) view.findViewById(R.id.txt_size_color);
        builder.setView(view);
        alert_dialog = builder.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txt_giap_truoc_khi_giam = (EditText) view.findViewById(R.id.txt_gia_truoc_khi_giam);
        txt_gia = (EditText) view.findViewById(R.id.txt_gia);
        sp_kichthuoc = (Spinner) view.findViewById(R.id.sp_kich_thuoc);
        sp_mausac = (Spinner) view.findViewById(R.id.sp_mau_sac);
        rb_rating = (RatingBar) view.findViewById(R.id.rb_rating);
        pager_hinh_sp = (ViewPager) view.findViewById(R.id.pager_hinh_sp);
        btn_browse_gallary = (ImageButton) view.findViewById(R.id.btn_browse_gallary);
        source_kichthuoc = new ArrayList<>();
        source_mausac = new ArrayList<>();
        txt_ten_sp = (EditText) view.findViewById(R.id.txt_ten_sp);
        bitmaps_hinh_sp = new ArrayList<>();
        adapter_hinh_sp = new AdapterHinhSp(getActivity().getSupportFragmentManager(), getContext(), bitmaps_hinh_sp);
        pager_hinh_sp.setAdapter(adapter_hinh_sp);
        btn_submit = (ImageButton) view.findViewById(R.id.btn_submit);
        path_hinh_sp = new ArrayList<>();
        createDialog();
        addEvents();
    }

    private void addEvents() {
        btn_browse_gallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuly_hienthi_pager_hinh_sp();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xacnhanUptoFirebase();
            }
        });

        btn_submit_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FLAG_DIALOG == 0) {
                    source_mausac.addAll(get_array_dialog());
                    xuly_hienthi_sp_mausac(source_mausac);
                } else if (FLAG_DIALOG == 1) {
                    source_kichthuoc.addAll(get_array_dialog());
                    xuly_hienthi_sp_kichthuoc(source_kichthuoc);
                }
                alert_dialog.dismiss();
            }
        });


        sp_kichthuoc.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                alert_dialog.show();
                FLAG_DIALOG = 1;
                return false;
            }
        });

        sp_kichthuoc.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                FLAG_DIALOG = 1;
                alert_dialog.show();
                return false;
            }
        });

        sp_mausac.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                FLAG_DIALOG = 0;
                alert_dialog.show();
                return false;
            }
        });

    }

    private void xacnhanUptoFirebase() {
        boolean contiue = false;
        contiue = check_txt_gia_truoc_khi_giam();
//        if (contiue) {
//            contiue = check_txt_gia_sp();
//        }
//        if (contiue) {
//            contiue = check_txt_ten_sp();
//        }
//        if (contiue) {
//            contiue = check_sp_kichthuoc();
//        }
//        if (contiue) {
//            contiue = check_pager_hinh_sp();
//        }
//        if (contiue) {
//            contiue = check_sp_mausac();
//        }
//        if(contiue) {
        transfer_and_move_phanloai_sp();
//        }
    }
    private boolean check_txt_ten_sp () {
        if (!txt_ten_sp.getText().toString().equals("") && null != txt_ten_sp.toString()) {
            return true;
        } else {
            txt_ten_sp.requestFocus();
            txt_ten_sp.setError("Your text is invalid or empty");
            return false;
        }
    }

    private void transfer_and_move_phanloai_sp() {
        get_set_sanpham uploadSanpham = new get_set_sanpham(getContext());
            for (int i = 0; i < bitmaps_hinh_sp.size(); i ++) {
                path_hinh_sp.add("/Sản phẩm/" + get_date_and_time());
            }
//        path_hinh_sp.add(path_image);
        uploadSanpham.Upload_images(bitmaps_hinh_sp, path_hinh_sp);
        uploadSanpham.set_on_upoad_images_listener(new get_set_sanpham.upload_images() {
            @Override
            public void on_upload_images() {
                trasnfer_motasp fragment_motasp = new fragment_motasp();
                String text = fragment_motasp.transfer_text();
                Sanpham.getInstance().setGiasp(Double.parseDouble(txt_gia.getText().toString()));
                Sanpham.getInstance().setGiaTruocKhiGiam(Double.parseDouble(txt_giap_truoc_khi_giam.getText().toString()));
                Sanpham.getInstance().setHinh(path_hinh_sp);

                Sanpham.getInstance().setIdsp(getActivity().getIntent().getStringExtra("uid")+ "+" + new Date().getTime());
                Sanpham.getInstance().setKichco(source_kichthuoc);
                Sanpham.getInstance().setMausac(source_mausac);
                Sanpham.getInstance().setMotachitietsp(text);
                Sanpham.getInstance().setRating(rb_rating.getRating());
                Sanpham.getInstance().setTensp(txt_ten_sp.getText().toString());
                Intent intent = new Intent(getActivity(), activity_phan_loai_sp.class);
                startActivity(intent);
            }
        });

    }

    private ArrayList<String> get_array_dialog () {
        String[] size_color = txt_size_color.getText().toString().split("\n");
        ArrayList<String> size_colors = new ArrayList<>();
        size_colors.addAll(Arrays.asList(size_color));
        return size_colors;
    }

    private boolean check_txt_gia_sp () {

        if (!txt_gia.getText().toString().equals("") && null == txt_gia.toString()) {
            return true;
        } else {
            txt_gia.requestFocus();
            txt_gia.setError("Your text is invalid or empty");
            return false;
        }
    }

    private String get_date_and_time () {
        Calendar calendar = Calendar.getInstance();
        String dateFormat = "ddmmyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        Date date = calendar.getTime();
        return simpleDateFormat.format(date) + date.getTime();
    }

    private boolean check_txt_gia_truoc_khi_giam () {

        if (!txt_giap_truoc_khi_giam.getText().toString().equals("") && null != txt_giap_truoc_khi_giam.toString()) {
            return true;
        } else {
            txt_giap_truoc_khi_giam.requestFocus();
            txt_giap_truoc_khi_giam.setError("Your text is invalid or empty");
            return false;
        }

    }

    private void xulyHienthiTxtCamKet () {

    }

    private boolean check_sp_mausac () {
        if (source_mausac.isEmpty()) {
            sp_mausac.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean check_sp_kichthuoc () {
        if (source_kichthuoc.isEmpty() ){
            sp_kichthuoc.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean check_pager_hinh_sp () {
        if (bitmaps_hinh_sp.isEmpty()) {
            pager_hinh_sp.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void xuly_hienthi_sp_kichthuoc (ArrayList<String> SourceKichco) {
        this.source_kichthuoc = new ArrayList<>();
        this.source_kichthuoc.addAll(SourceKichco);
        adapter_sp_kichthuoc = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, this.source_kichthuoc);
        sp_kichthuoc.setAdapter(adapter_sp_kichthuoc);
    }

    private void xuly_hienthi_sp_mausac (ArrayList<String> SourceMausac) {
        this.source_mausac = new ArrayList<>();
        this.source_mausac.addAll(SourceMausac);
        adapter_sp_mausac = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, this.source_mausac);
        sp_mausac.setAdapter(adapter_sp_mausac);
    }

    private void xuly_hienthi_pager_hinh_sp () {
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
                bitmaps_hinh_sp.add(selectedImage);
                adapter_hinh_sp.notifyDataSetChanged();
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
    public void on_get_sanpham(Sanpham sanpham) {

    }

    public interface trasnfer_motasp {
        String transfer_text();
    }
}
