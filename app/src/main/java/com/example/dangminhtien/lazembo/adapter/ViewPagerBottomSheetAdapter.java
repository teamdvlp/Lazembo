package com.example.dangminhtien.lazembo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dangminhtien.lazembo.Fragment.Account_Fragment;
import com.example.dangminhtien.lazembo.Fragment.Cart_Fragment;

/**
 * Created by phamf on 04-Jun-17.
 */

public class ViewPagerBottomSheetAdapter extends FragmentPagerAdapter {

    public ViewPagerBottomSheetAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Cart_Fragment();
            case 1:
                return new Account_Fragment();
        }

        return new Account_Fragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Giỏ hàng";
            case 1:
                return "Thông tin";
        }

        return super.getPageTitle(position);
    }
}
