package app.dao;

import entities.Team;

import java.sql.SQLException;

public interface TeamDAO {
    Team getTeamById(int id) throws SQLException;
}
