package dao;

import entities.Comment;
import entities.Post;
import entities.Sport;
import entities.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface CommentDAO {
    void newComment(User author, Post post, Date datetime, String text) throws SQLException;
    boolean deleteComment(int id) throws SQLException;
    Comment getCommentById(int id) throws SQLException;
    List<Comment> getCommentsByPost(Post post) throws SQLException;
}
