package com.haroldxie.myblog.Domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Blog implements Serializable {
    private int blog_id;
    private int author_id;
    private String authorName;
    private String title;
    private String tags_id;
    private int category_id;
    private Timestamp createdTime;
    private Timestamp lastEditedTime;
    private int readingTime;
    private String content;
    private String picPath;

    public Blog() {}

    public Blog(int blog_id, int author_id, String title, String tags_id, int category_id, Timestamp createdTime, Timestamp lastEditedTime, int readingTime, String content, String picPath) {
        this.blog_id = blog_id;
        this.author_id = author_id;
        this.title = title;
        this.tags_id = tags_id;
        this.category_id = category_id;
        this.createdTime = createdTime;
        this.lastEditedTime = lastEditedTime;
        this.readingTime = readingTime;
        this.content = content;
        this.picPath = picPath;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags_id() {
        return tags_id;
    }

    public void setTags_id(String tags_id) {
        this.tags_id = tags_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getLastEditedTime() {
        return lastEditedTime;
    }

    public void setLastEditedTime(Timestamp lastEditedTime) {
        this.lastEditedTime = lastEditedTime;
    }

    public int getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(int readingTime) {
        this.readingTime = readingTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blog_id=" + blog_id +
                ", author_id=" + author_id +
                ", title='" + title + '\'' +
                ", tags_id='" + tags_id + '\'' +
                ", category_id=" + category_id +
                ", createdTime=" + createdTime +
                ", lastEditedTime=" + lastEditedTime +
                ", readingTime=" + readingTime +
                ", content='" + content + '\'' +
                ", picPath='" + picPath + '\'' +
                '}';
    }
}
