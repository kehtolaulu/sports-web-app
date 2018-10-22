package services;

import dao.PostDAO;
import entities.Post;
import entities.Sport;
import entities.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PostService {
    private PostDAO postDAO;

    public PostService() {
        this.postDAO = new dao.postgresdao.PostDAO();
    }

    public void newPost(User author, String title, String text, Sport sport) throws SQLException {
        postDAO.newPost(author, title, text, new java.sql.Date(new Date().getTime()), sport);
    }

    public List<Post> getAllPosts() {
        try {
            return postDAO.getAllPosts();
        } catch (SQLException e) {
            return new LinkedList<>();
        }
    }

    public Post getPostById(int id) {
        try {
            return postDAO.getPostById(id);
        } catch (SQLException e) {
            return null;
        }
    }

    public void deletePost(int id) throws SQLException {
        postDAO.deletePost(id);
    }

    public void updatePost(int id, String text, String title) {
        try {
            postDAO.updatePost(id, text, title);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Post> getPostsByAuthor(User author) {
        try {
            return postDAO.getPostsByAuthor(author);
        } catch (SQLException e) {
            return new LinkedList<>();
        }
    }
}
