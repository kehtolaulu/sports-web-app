package services;

import dao.CommentDAO;
import dao.PostDAO;
import entities.Post;
import entities.Sport;
import entities.User;

import java.sql.Date;
import java.sql.SQLException;

public class CommentService {
    private CommentDAO commentDAO;

    public CommentService() {
        this.commentDAO = new dao.postgresdao.CommentDAO();
    }

    public void newComment(User author, Post post, String text) throws SQLException {
        commentDAO.newComment(author, post, new java.sql.Date(new java.util.Date().getTime()), text);
    }
    public void deleteComment(int id) {
        commentDAO.deleteComment(id);
    }
}
