package entities;

import java.util.Date;

public class Comment {
    private int id;
    private Integer authorId;

    private User author;

    private Integer postId;
    private Post post;
    private Date datetime; //?
    private String text;

    public Comment(int id, User author, Post post, Date datetime, String text) {
        this.id = id;
        this.author = author;
        this.post = post;
        this.datetime = datetime;
        this.text = text;
    }

    public Comment() {

    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
