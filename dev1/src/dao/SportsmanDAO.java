package dao;

import entities.Sportsman;

import java.sql.SQLException;
import java.util.List;

public interface SportsmanDAO {
    List<Sportsman> getAllSportsmen() throws SQLException;
    Sportsman getSportsmanById(int id) throws SQLException;
}
