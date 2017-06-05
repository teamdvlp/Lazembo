package com.example.dangminhtien.lazembo.Model;

/**
 * Created by tiend on 6/5/2017.
 */

public class refresh {
    refresh_data refresh_data;
    public refresh () {

    }

    public void refreshData () {
        refresh_data.onrefresh_data();
    }

    public void setOnData_refresh (refresh_data refresh_data) {
        this.refresh_data = refresh_data;
    }

    public interface refresh_data {
        public void onrefresh_data ();
    }
}
