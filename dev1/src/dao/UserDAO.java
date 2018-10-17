package dao;

import entities.User;

import java.sql.SQLException;

public interface UserDAO {
    User getByLogin(String username) throws SQLException;
}
