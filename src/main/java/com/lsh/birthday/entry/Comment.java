package com.lsh.birthday.entry;

public class Comment {
    
    private Long commentId;
    private String context;
    
    public Comment(String context) {
        this.context = context;
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

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", context='" + context + '\'' +
                '}';
    }
}
