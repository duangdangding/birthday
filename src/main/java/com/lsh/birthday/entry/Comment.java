package com.lsh.birthday.entry;

import java.io.Serializable;
import java.sql.Timestamp;

public class Comment implements Serializable {
    
    private Long commentId;
    private String context;
    private Timestamp createtime;
    
    public Comment() {
    }
    public Comment(String context,Timestamp createtime) {
        this.context = context;
        this.createtime = createtime;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", context='" + context + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}
