package dao.postgresdao;

import entities.Post;
import entities.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentDAO implements dao.CommentDAO {

    private Connection connection;
    private dao.UserDAO userDAO;
    private SportDAO sportDAO;

    public CommentDAO() {
        this.connection = ConnectionSingleton.getInstance();
        this.userDAO = new dao.postgresdao.UserDAO();
        this.sportDAO = new dao.postgresdao.SportDAO();
    }

    @Override
    public void newComment(User author, Post post, Date datetime, String text) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO comment (author_id, post_id, datetime, text) VALUES (?, ?, ?, ?)");
        statement.setInt(1, author.getId());
        statement.setInt(2, post.getId());
        statement.setDate(3, datetime);
        statement.setString(4, text);
        statement.execute();
    }

    @Override
    public boolean deleteComment(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM comment WHERE id = ?");
        statement.setInt(1, id);
        return statement.execute();
    }
}
