package com.example.dangminhtien.lazembo.data;

import java.util.ArrayList;

public class Khachhang {
    private String email;
    private String uid;
    private String HOVATEN;
    private String sdt;
    private ArrayList<String> sanphams;
    private boolean nguoiban;


    public Khachhang(String HOVATEN, String email, String sdt, boolean nguoiban, ArrayList<String> sanphams, String uid) {
        this.uid = uid;
        this.HOVATEN = HOVATEN;
        this.email = email;
        this.sdt = sdt;
        this.nguoiban = nguoiban;
        this.sanphams = sanphams;
    }

    public Khachhang () {

    }

    public ArrayList<String> getSanphams() {
        return sanphams;
    }

    public void setSanphams(ArrayList<String> sanphams) {
        this.sanphams = sanphams;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getHOVATEN() {
        return HOVATEN;
    }

    public void setHOVATEN(String HOVATEN) {
        this.HOVATEN = HOVATEN;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public boolean isNguoiban() {
        return nguoiban;
    }

    public void setNguoiban(boolean nguoiban) {
        this.nguoiban = nguoiban;
    }

}
