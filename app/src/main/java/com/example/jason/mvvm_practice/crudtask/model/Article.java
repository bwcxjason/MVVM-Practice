package com.example.jason.mvvm_practice.crudtask.model;

import java.io.Serializable;

public class Article implements Serializable {

    private int articleID;
    private String imageUrl;
    private String issuanceTime;
    private String linkUrl;
    private String nickname;
    private int pageviewCount;
    private String summary;
    private String title;

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIssuanceTime() {
        return issuanceTime;
    }

    public void setIssuanceTime(String issuanceTime) {
        this.issuanceTime = issuanceTime;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPageviewCount() {
        return pageviewCount;
    }

    public void setPageviewCount(int pageviewCount) {
        this.pageviewCount = pageviewCount;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
