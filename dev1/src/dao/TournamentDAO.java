package dao;

import entities.Tournament;

import java.sql.SQLException;

public interface TournamentDAO {
    public Tournament getTournamentById(int id) throws SQLException;
}
