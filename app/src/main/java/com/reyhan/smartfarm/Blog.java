package com.reyhan.smartfarm;

public class Blog extends BlogPostId {

    private String judul, desc;

    public Blog() {

    }

    public Blog(String judul, String desc) {
        this.judul = judul;
        this.desc = desc;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
