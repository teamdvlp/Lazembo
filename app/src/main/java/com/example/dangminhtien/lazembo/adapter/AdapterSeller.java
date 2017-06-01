package com.example.dangminhtien.lazembo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by dangminhtien on 5/30/17.
 */

public class AdapterSeller extends FragmentPagerAdapter {
    public AdapterSeller(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
            if (position == 0) {
                return com.example.dangminhtien.lazembo.Fragment.fragment_layout_sp.newInstance(1);

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
