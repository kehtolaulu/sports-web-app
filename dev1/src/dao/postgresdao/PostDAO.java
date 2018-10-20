package dao.postgresdao;

import dao.UserDAO;
import entities.Post;
import entities.Sport;
import entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO implements dao.PostDAO {
    private Connection connection;
    private UserDAO userDAO;
    private SportDAO sportDAO;

    public PostDAO() {
        this.connection = ConnectionSingleton.getInstance();
        this.userDAO = new dao.postgresdao.UserDAO();
        this.sportDAO = new dao.postgresdao.SportDAO();
    }

    @Override
    public List<Post> getAllPosts() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM post");
        List<Post> posts = new ArrayList<>();
        while (resultSet.next()) {
            posts.add(new Post(
                    resultSet.getInt("id"),
                    userDAO.getUserById(resultSet.getInt("author_id")),
                    resultSet.getString("title"),
                    resultSet.getString("text"),
                    resultSet.getDate("datetime"),
                    sportDAO.getSportById(resultSet.getInt("sport_id"))
            ));
        }
        return posts;
    }

    @Override
    public void newPost(User author, String title, String text, Date datetime, Sport sport) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO post (author_id, title, text, datetime, sport_id) VALUES (?, ?, ?, ?, ?)");
        statement.setInt(1, author.getId());
        statement.setString(2, title);
        statement.setString(3, text);
        statement.setDate(4, datetime);
        statement.setInt(5, sport.getId());
        statement.execute();
    }

    @Override
    public Post getPostById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM post WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Post(
                    resultSet.getInt("id"),
                    userDAO.getUserById(resultSet.getInt("author_id")),
                    resultSet.getString("title"),
                    resultSet.getString("text"),
                    resultSet.getDate("datetime"),
                    sportDAO.getSportById(resultSet.getInt("sport_id"))
            );
        }
        return null;
    }

    @Override
    public boolean deletePost(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM post WHERE id = ?");
        statement.setInt(1, id);
        return statement.execute();
    }

    @Override
    public boolean updatePost(int id, String text, String title) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE post SET text = ?, title = ? WHERE id = ?");
        statement.setString(1, text);
        statement.setString(2, title);
        statement.setInt(3, id);
        return statement.execute();
    }

    @Override
    public List<Post> getPostsByAuthor(User author) throws SQLException {
        List<Post> posts = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM post WHERE author_id = ?");
        statement.setInt(1, author.getId());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Post post = new Post(
                    resultSet.getInt("id"),
                    userDAO.getUserById(author.getId()),
                    resultSet.getString("title"),
                    resultSet.getString("text"),
                    resultSet.getDate("datetime"),
                    sportDAO.getSportById(resultSet.getInt("sport_id"))
            );
            posts.add(post);
        }
        return posts;

    }


}
