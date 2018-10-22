package entities;

import java.util.Date;

public class Post {
    private int id;

    private Integer authorId;
    private User author;
    private String title;
    private String text;
    private Date datetime;

    public Post(int id, User author, String title, String text, Date datetime) {
        this.id = id;
        this.author = author;
        this.authorId = author.getId();
        this.title = title;
        this.text = text;
        this.datetime = datetime;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
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
        this.authorId = author.getId();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

}
