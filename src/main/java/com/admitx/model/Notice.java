package com.admitx.model;

public class Notice {

    private String noticeId;
    private String title;
    private String description;
    private String tag;
    private boolean published;
    private long createdAt;

    public Notice() {
    }

    public Notice(
            String noticeId,
            String title,
            String description,
            String tag,
            boolean published,
            long createdAt
    ) {
        this.noticeId = noticeId;
        this.title = title;
        this.description = description;
        this.tag = tag;
        this.published = published;
        this.createdAt = createdAt;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}