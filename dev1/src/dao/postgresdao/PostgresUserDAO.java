package dao.postgresdao;

import dao.UserDAO;
import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgresUserDAO implements UserDAO {
    Connection connection;

    public PostgresUserDAO() {
        connection = ConnectionSingleton.getInstance();
    }

    @Override
    public User getByLogin(String username) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"user\" WHERE login = ?");
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new User(
                    resultSet.getInt("id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("name")
            );
        }
        return null;
    }
}
