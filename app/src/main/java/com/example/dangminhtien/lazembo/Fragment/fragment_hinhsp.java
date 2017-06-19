package com.example.dangminhtien.lazembo.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dangminhtien.lazembo.R;

import java.util.ArrayList;

public class fragment_hinhsp extends Fragment {
    private static final String KEY_POSITION = "position";
    private static final String KEY_HINH = "hinh";

    private int position;
    private ImageView imgHinhsp;

    public fragment_hinhsp() {
    }

    public static fragment_hinhsp newInstance(int position) {
        fragment_hinhsp fragment = new fragment_hinhsp();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(KEY_POSITION);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_hinhsp, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgHinhsp = (ImageView) view.findViewById(R.id.imghinhsp);
        imgHinhsp.setImageBitmap(fragment_product.bitmaps_hinh_sp.get(position));

    }
}

