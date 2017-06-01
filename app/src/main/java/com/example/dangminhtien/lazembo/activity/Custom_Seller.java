package com.example.dangminhtien.lazembo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AlignmentSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.adapter.AdapterSeller;

public class Custom_Seller extends AppCompatActivity {
    EditText txtGiasp, txtGiapSpTruongkhigiam, txt;
    TextView txtCamket, txtHinhthucthanhtoan;
    RatingBar rbRatingsp;
    ViewPager pagerHinhsp;
    Spinner spMausac, spKichco;
    ViewPager pagerCustomsp;
    TabLayout tabTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_seller);
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        addControls();
        addevents();
    }

    private void addControls() {
//        txtCamket = (TextView) findViewById(R.id.txtCamket);
//        txtGiapSpTruongkhigiam = (EditText) findViewById(R.id.txtGiaspTruockhigiam);
//        txtGiasp = (EditText) findViewById(R.id.txtGiasp);
//        spKichco = (Spinner) findViewById(R.id.spKichco);
//        spMausac = (Spinner) findViewById(R.id.spMausac);
//        rbRatingsp = (RatingBar) findViewById(R.id.rbRatingsp);
//        pagerHinhsp = (ViewPager) findViewById(R.id.pagerHinhsp);
        pagerCustomsp = (ViewPager) findViewById(R.id.Pager_CustomSp);
        tabTitle = (TabLayout) findViewById(R.id.tabTitle);
        AdapterSeller adapterSeller = new AdapterSeller(getSupportFragmentManager());
        pagerCustomsp.setAdapter(adapterSeller);

    }

    private void addevents() {

    }

    private void xulyTxtGiasp () {

    }

    private void xulyTxtGiaspTruocKhiGiam () {

    }

    private void xylyTxtCamKet () {

    }

    private void xulySpMausac () {

    }

    private void xulySpKichco () {

    }

    private void xulyRbRating () {

    }

    private void xulyPagerHinhsp () {

    }

    private void xulyHienthiSpKichco () {

    }

    private void xylyHienthiSpMausac () {

    }

    private void xulyHienthinRbRating () {

    }

    private void xulyHienthiPagerHinhsp () {

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

}
