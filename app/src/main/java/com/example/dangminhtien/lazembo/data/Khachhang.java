package com.example.dangminhtien.lazembo.data;

import java.util.HashMap;

public class Khachhang {
    private String email;
    private String uid;
    private String HOVATEN;
    private String sdt;
    private HashMap<String,String> sanphams;
    private boolean nguoiban;


    public Khachhang(String HOVATEN, String email, String sdt, boolean nguoiban, HashMap<String,String> sanphams, String uid) {
        this.uid = uid;
        this.HOVATEN = HOVATEN;
        this.email = email;
        this.sdt = sdt;
        this.nguoiban = nguoiban;
        this.sanphams = sanphams;
    }

    public Khachhang () {

    }

    public HashMap<String, String> getSanphams() {
        return sanphams;
    }

    public void setSanphams(HashMap<String, String> sanphams) {
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
