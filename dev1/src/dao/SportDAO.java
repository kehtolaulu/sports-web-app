package app.dao;

import entities.Sport;

import java.sql.SQLException;
import java.util.List;

public interface SportDAO {
    Sport getSportById(int id) throws SQLException;

    Sport getSportByName(String name) throws SQLException;

    List<Sport> getAllSports() throws SQLException;
}
