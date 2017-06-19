package com.example.dangminhtien.lazembo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dangminhtien.lazembo.Fragment.fragment_product;

/**
 * Created by dangminhtien on 5/30/17.
 */

public class adapter_product extends FragmentPagerAdapter {
    public adapter_product(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
            if (position == 0) {
                return fragment_product.newInstance(1);

            } else if (position == 1) {
                return com.example.dangminhtien.lazembo.Fragment.fragment_motasp.newInstance("", "");
            }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
