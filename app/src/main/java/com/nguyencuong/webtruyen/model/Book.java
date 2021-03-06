package com.nguyencuong.webtruyen.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nguyencuong.webtruyen.Constants;

/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 4/15/2017.
 * Email: vancuong2941989@gmail.com
 */

public class Book {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("type_book")
    @Expose
    private String bookType;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name2")
    @Expose
    private String name2;
    @SerializedName("poster")
    @Expose
    private String poster;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("share_link")
    @Expose
    private String shareLink;
    @SerializedName("fb_comment")
    @Expose
    private String fbComment;
    @SerializedName("total_chapter")
    @Expose
    private int totalChapter;
    @SerializedName("curent_chapter")
    @Expose
    private int curentChapter;
    @SerializedName("view")
    @Expose
    private int viewed;
    @SerializedName("liked")
    @Expose
    private int liked;
    @SerializedName("point_rating")
    @Expose
    private int pointRating;
    @SerializedName("total_rating")
    @Expose
    private int totalRating;
    @SerializedName("update_at")
    @Expose
    private long updateAt;
    @SerializedName("audio")
    @Expose
    private boolean audio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        if (category == null) return "";
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBookType() {
        if (bookType == null) return "";
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getName() {
        if (name == null) return "";
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        if (name2 == null) return "";
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getPoster() {
        if (poster == null) return "";
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getAuthor() {
        if (author == null) return "";
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        if (description == null) return "";
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShareLink() {
        if (shareLink == null || shareLink.length() < 5) {
            return Constants.HOME_URL;
        }
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
    }

    public String getFbComment() {
        if (shareLink == null || shareLink.length() < 5) {
            return Constants.HOME_URL;
        }
        return fbComment;
    }

    public void setFbComment(String fbComment) {
        this.fbComment = fbComment;
    }

    public int getTotalChapter() {
        return totalChapter;
    }

    public void setTotalChapter(int totalChapter) {
        this.totalChapter = totalChapter;
    }

    public int getCurentChapter() {
        return curentChapter;
    }

    public void setCurentChapter(int curentChapter) {
        this.curentChapter = curentChapter;
    }

    public int getViewed() {
        return viewed;
    }

    public void setViewed(int viewed) {
        this.viewed = viewed;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public int getPointRating() {
        return pointRating;
    }

    public void setPointRating(int pointRating) {
        this.pointRating = pointRating;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public String getRating() {
        if (totalRating == 0 || pointRating == 0) {
            return "00";
        }

        double rating = Math.round(pointRating * 10 / totalRating) / 10;
        if (rating == 10) {
            return "10";
        } else {
            return String.valueOf(rating);
        }
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(int updateAt) {
        this.updateAt = updateAt;
    }

    public boolean isAudio() {
        return audio;
    }

    public void setAudio(boolean audio) {
        this.audio = audio;
    }
}
