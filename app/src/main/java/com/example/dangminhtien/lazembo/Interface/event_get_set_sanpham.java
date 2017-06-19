package com.example.dangminhtien.lazembo.Interface;

import android.graphics.Bitmap;

import com.example.dangminhtien.lazembo.data.Sanpham;

import java.util.ArrayList;

/**
 * Created by dangminhtien on 6/10/17.
 */

public interface event_get_set_sanpham {

        public void on_get_image(Bitmap bitmap);

        public void on_get_sanpham(Sanpham sanpham);

        public void on_get_sanphams(ArrayList<Sanpham> sanphams);

        public void on_upload_image(String url);

        public void on_upload_images();

        public void on_get_images(ArrayList<Bitmap> bitmaps);
}
