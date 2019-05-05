package com.reyhan.smartfarm;

public class Profil {
    private String nama;
    private String alamat;
    private String pendidikan;

    public Profil(){

    }

    public Profil(String nama, String alamat, String pendidikan) {
        this.nama = nama;
        this.alamat = alamat;
        this.pendidikan = pendidikan;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getPendidikan() {
        return pendidikan;
    }
}
