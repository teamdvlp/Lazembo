package com.example.dangminhtien.lazembo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dangminhtien.lazembo.Fragment.fragment_hinhsp;

import java.util.ArrayList;

public class AdapterHinhSp extends FragmentPagerAdapter {
    private static int count;
    Context context;
    ArrayList<Bitmap> bitmaps;
    public AdapterHinhSp(FragmentManager fm, Context context, ArrayList<Bitmap> bitmaps) {
        super(fm);
        this.count = count;
        this.context = context;
        this.bitmaps = bitmaps;
    }

    @Override
    public Fragment getItem(int position) {
        return fragment_hinhsp.newInstance(position);
    }

    @Override
    public int getCount() {
        return bitmaps.size();
    }
}
