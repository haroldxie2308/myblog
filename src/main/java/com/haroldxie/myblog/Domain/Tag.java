package com.haroldxie.myblog.Domain;

public class Tag {
    private int tag_id;
    private String tagName;

    public Tag(int tag_id, String tagName) {
        this.tag_id = tag_id;
        this.tagName = tagName;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
