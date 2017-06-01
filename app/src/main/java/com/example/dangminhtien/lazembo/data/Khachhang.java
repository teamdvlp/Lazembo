package com.example.dangminhtien.lazembo.data;

public class Khachhang {
    private String id;
    private String pass;
    private String HOVATEN;
    private String email;
    private String sdt;
    private Giohang giohang;
    private boolean nguoiban;
    private byte gioitinh;

    public Khachhang(String id, String pass, String HOVATEN, String email, String sdt, Giohang giohang, boolean nguoiban, byte gioitinh) {
        this.id = id;
        this.pass = pass;
        this.HOVATEN = HOVATEN;
        this.email = email;
        this.sdt = sdt;
        this.giohang = giohang;
        this.nguoiban = nguoiban;
        this.gioitinh = gioitinh;
    }

    public Khachhang () {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

    public Giohang getGiohang() {
        return giohang;
    }

    public void setGiohang(Giohang giohang) {
        this.giohang = giohang;
    }

    public boolean isNguoiban() {
        return nguoiban;
    }

    public void setNguoiban(boolean nguoiban) {
        this.nguoiban = nguoiban;
    }

    public byte getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(byte gioitinh) {
        this.gioitinh = gioitinh;
    }
}
