package com.nguyencuong.webtruyen.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 4/25/2017.
 * Email: vancuong2941989@gmail.com
 */

public class Chapter {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("number")
    @Expose
    private int number;
    @SerializedName("content_trans")
    @Expose
    private String contentTrans;
    @SerializedName("content_cvt")
    @Expose
    private String contentCvt;
    @SerializedName("content_audio")
    @Expose
    private String contentAudio;

    public Chapter() {
    }

    public Chapter(int id, String name, int number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getContentTrans() {
        return contentTrans;
    }

    public void setContentTrans(String contentTrans) {
        this.contentTrans = contentTrans;
    }

    public String getContentCvt() {
        return contentCvt;
    }

    public void setContentCvt(String contentCvt) {
        this.contentCvt = contentCvt;
    }

    public String getContentAudio() {
        return contentAudio;
    }

    public void setContentAudio(String contentAudio) {
        this.contentAudio = contentAudio;
    }
}
