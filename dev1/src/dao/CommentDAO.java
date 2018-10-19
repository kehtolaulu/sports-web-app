package dao;

import entities.Post;
import entities.Sport;
import entities.User;

import java.sql.Date;
import java.sql.SQLException;

public interface CommentDAO {
    void newComment(User author, Post post, Date datetime, String text) throws SQLException;
    boolean deleteComment(int id) throws SQLException;
}
