package dao.postgresdao;

import dao.PostDAO;
import entities.Comment;
import entities.Post;
import entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO implements dao.CommentDAO {

    private Connection connection;
    private dao.UserDAO userDAO;
    private SportDAO sportDAO;
    private PostDAO postDAO;

    public CommentDAO() {
        this.connection = ConnectionSingleton.getInstance();
        this.userDAO = new dao.postgresdao.UserDAO();
        this.sportDAO = new dao.postgresdao.SportDAO();
        this.postDAO = new dao.postgresdao.PostDAO();
    }

    @Override
    public Comment newComment(User author, Post post, Date datetime, String text) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO comment (author_id, post_id, datetime, text) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, author.getId());
        statement.setInt(2, post.getId());
        statement.setDate(3, datetime);
        statement.setString(4, text);
        statement.execute();
        statement.executeUpdate();
        ResultSet keys = statement.getGeneratedKeys();
        return instance(keys);
    }

    @Override
    public boolean deleteComment(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM comment WHERE id = ?");
        statement.setInt(1, id);
        return statement.execute();
    }

    @Override
    public Comment getCommentById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM comment WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        return instance(resultSet);
    }

    @Override
    public List<Comment> getCommentsByPost(Post post) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM comment WHERE post_id = ?");
        statement.setInt(1, post.getId());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Comment comment = new Comment(
                    resultSet.getInt("id"),
                    post.getAuthor(),
                    post,
                    resultSet.getDate("datetime"),
                    resultSet.getString("text")
            );
            comments.add(comment);
        }
        return comments;
    }

    protected Comment instance(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return new Comment(
                    rs.getInt("id"),
                    userDAO.getUserById(rs.getInt("author_id")),
                    postDAO.getPostById(rs.getInt("post_id")),
                    rs.getDate("datetime"),
                    rs.getString("text")
            );
        } else {
            return null;
        }
    }
}
