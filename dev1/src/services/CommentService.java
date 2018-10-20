package services;

import dao.CommentDAO;
import dao.PostDAO;
import entities.Comment;
import entities.Post;
import entities.Sport;
import entities.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CommentService {
    private CommentDAO commentDAO;

    public CommentService() {
        this.commentDAO = new dao.postgresdao.CommentDAO();
    }

    public void newComment(User author, Post post, String text) throws SQLException {
        commentDAO.newComment(author, post, new java.sql.Date(new java.util.Date().getTime()), text);
    }
    public void deleteComment(int id) {
        try {
            commentDAO.deleteComment(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Comment getCommentById(int id) {
        try {
            return commentDAO.getCommentById(id);
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Comment> getCommentsByPost(Post post) {
        try {
            return commentDAO.getCommentsByPost(post);
        } catch (SQLException e) {
            return new LinkedList<>();
        }
    }
}
