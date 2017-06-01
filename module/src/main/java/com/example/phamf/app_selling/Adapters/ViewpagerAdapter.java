package com.example.phamf.app_selling.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.phamf.app_selling.Fragment_package.Car_Fragment;
import com.example.phamf.app_selling.Fragment_package.Electricity_Fragment;
import com.example.phamf.app_selling.Fragment_package.Fashion_Fragment;
import com.example.phamf.app_selling.Fragment_package.Food_Fragment;
import com.example.phamf.app_selling.Fragment_package.Houseware_Fragment;
import com.example.phamf.app_selling.Fragment_package.Sale_Off_Fragment;

/**
 * Created by phamf on 28-May-17.
 */

public class ViewpagerAdapter extends FragmentPagerAdapter {

    public ViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Sale_Off_Fragment();
            case 1:
                return new Electricity_Fragment();
            case 2:
                return new Fashion_Fragment();
            case 3:
                return new Food_Fragment();
            case 4:
                return new Houseware_Fragment();
            case 5:
                return new Car_Fragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Khuyến mãi";
            case 1:
                return "Đồ điện tử";
            case 2:
                return "Thời Trang";
            case 3:
                return "Thức ăn";
            case 4:
                return "Đồ gia dụng";
            case 5:
                return "Xe";
        }
        return super.getPageTitle(position);
    }
}
