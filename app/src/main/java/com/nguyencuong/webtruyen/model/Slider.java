package com.nguyencuong.webtruyen.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The properties of slider
 * Created by pc on 4/14/2017.
 */

public class Slider implements Parcelable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("book_id")
    @Expose
    private int bookId;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("link")
    @Expose
    private String link;

    protected Slider(Parcel in) {
        id = in.readInt();
        type = in.readInt();
        bookId = in.readInt();
        img = in.readString();
        link = in.readString();
    }

    public static final Creator<Slider> CREATOR = new Creator<Slider>() {
        @Override
        public Slider createFromParcel(Parcel in) {
            return new Slider(in);
        }

        @Override
        public Slider[] newArray(int size) {
            return new Slider[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(type);
        parcel.writeInt(bookId);
        parcel.writeString(img);
        parcel.writeString(link);
    }
}
