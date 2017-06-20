package com.example.dangminhtien.lazembo.data;

import java.util.ArrayList;

public class Sanpham {

    private static Sanpham sanpham = new Sanpham();
    private int soluong;
    private String tensp;
    private ArrayList<String> kichco;
    private ArrayList<String> mausac;
    private double rating;
    private double giasp;
    private String motachitietsp;
    private ArrayList<String> hinh;
    private double giaTruocKhiGiam;
    private String idsp;

    public Sanpham(String tensp, ArrayList<String> kichco, ArrayList<String> mausac, double rating, double giasp, String motachitietsp, ArrayList<String> hinh, double giaTruocKhiGiam, String idsp) {
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

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public static Sanpham getInstance() {
        return Sanpham.sanpham;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getGiasp() {
        return giasp;
    }

    public void setGiasp(double giasp) {
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

    public double getGiaTruocKhiGiam() {
        return giaTruocKhiGiam;
    }

    public void setGiaTruocKhiGiam(double giaTruocKhiGiam) {
        this.giaTruocKhiGiam = giaTruocKhiGiam;
    }

    public String getIdsp() {
        return idsp;
    }

    public void setIdsp(String idsp) {
        this.idsp = idsp;
    }
}
