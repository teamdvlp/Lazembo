package com.example.dangminhtien.lazembo.data;

import java.util.ArrayList;

public class Sanpham {

    private String tensp;
    private ArrayList<String> kichco;
    private ArrayList<String> mausac;
    private float rating;
    private long giasp;
    private String motachitietsp;
    private ArrayList<String> hinh;
    private float giaTruocKhiGiam;
    private String idsp;

    public Sanpham(String tensp, ArrayList<String> kichco, ArrayList<String> mausac, float rating, long giasp, String motachitietsp, ArrayList<String> hinh, float giaTruocKhiGiam, String idsp) {
        this.tensp = tensp;
        this.kichco = kichco;
        this.mausac = mausac;
        this.rating = rating;
        this.giasp = giasp;
        this.motachitietsp = motachitietsp;
        this.hinh = hinh;
        this.giaTruocKhiGiam = giaTruocKhiGiam;
        this.idsp = idsp;
    }

    public Sanpham () {

    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public ArrayList<String> getKichco() {
        return kichco;
    }

    public void setKichco(ArrayList<String> kichco) {
        this.kichco = kichco;
    }

    public ArrayList<String> getMausac() {
        return mausac;
    }

    public void setMausac(ArrayList<String> mausac) {
        this.mausac = mausac;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public long getGiasp() {
        return giasp;
    }

    public void setGiasp(long giasp) {
        this.giasp = giasp;
    }

    public String getMotachitietsp() {
        return motachitietsp;
    }

    public void setMotachitietsp(String motachitietsp) {
        this.motachitietsp = motachitietsp;
    }

    public ArrayList<String> getHinh() {
        return hinh;
    }

    public void setHinh(ArrayList<String> hinh) {
        this.hinh = hinh;
    }

    public float getGiaTruocKhiGiam() {
        return giaTruocKhiGiam;
    }

    public void setGiaTruocKhiGiam(float giaTruocKhiGiam) {
        this.giaTruocKhiGiam = giaTruocKhiGiam;
    }

    public String getIdsp() {
        return idsp;
    }

    public void setIdsp(String idsp) {
        this.idsp = idsp;
    }
}
