package com.example.dangminhtien.lazembo.helper;

import android.content.res.Resources;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class helper {
    int  a;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int get_density_dpi (Resources resources) {
        return resources.getDisplayMetrics().densityDpi;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int convert_dp_to_px (Resources resources, int dp) {
        int density_dpi = get_density_dpi(resources);
        float px = dp*density_dpi/160;
        DecimalFormat format = new DecimalFormat("#####");
        return Integer.valueOf(format.format(px));
    }

    public String getDateAndTime () {
        Calendar calendar = Calendar.getInstance();
        String dateFormat = "ddmmyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        Date date = calendar.getTime();
        return simpleDateFormat.format(date) + date.getTime();
    }
}
