package dao;

import entities.User;

import java.sql.SQLException;

public interface UserDAO {
    User getUserByLogin(String username) throws SQLException;
    User getUserById(int id) throws SQLException;
    boolean addUser(User user) throws SQLException;
    boolean addToken(User user, String token) throws SQLException;
    boolean updateUser(int id, String password, String name) throws SQLException;
}
