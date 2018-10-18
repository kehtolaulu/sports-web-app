package dao.postgresdao;

import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements dao.UserDAO {
    Connection connection;

    public UserDAO() {
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

    @Override
    public boolean addUser(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO \"user\" (login, password, name) VALUES (?, ?, ?)");
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getName());
        return statement.execute();
    }
}
