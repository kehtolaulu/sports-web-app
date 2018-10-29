package dao;

import entities.Comment;
import entities.Post;
import entities.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface CommentDAO {
    Comment newComment(User author, Post post, Date datetime, String text) throws SQLException;

    boolean deleteComment(int id) throws SQLException;

    List<Comment> getCommentsByPost(Post post) throws SQLException;
}
