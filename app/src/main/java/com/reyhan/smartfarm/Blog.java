package com.reyhan.smartfarm;

public class Blog extends BlogPostId {

    private String judul;
    private String desc;

    public Blog() {
    }

    public Blog(String judul, String desc) {
        this.judul = judul;
        this.desc = desc;
    }

    public String getJudul() {
        return judul;
    }

    public String getDesc() {
        return desc;
    }
}
