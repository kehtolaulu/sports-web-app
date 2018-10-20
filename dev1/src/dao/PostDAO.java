package dao;

import entities.Post;
import entities.Sport;
import entities.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface PostDAO {
    List<Post> getAllPosts() throws SQLException;
    void newPost(User author, String title, String text, Date datetime, Sport sport) throws SQLException;
    Post getPostById(int id) throws SQLException;
    boolean deletePost(int id) throws SQLException;
    boolean updatePost(int id, String text, String title) throws SQLException;
    List<Post> getPostsByAuthor(User author) throws SQLException;
}
