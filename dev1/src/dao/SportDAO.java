package dao;

import entities.Sport;

import java.sql.SQLException;

public interface SportDAO {
    Sport getSportById(int id) throws SQLException;
}