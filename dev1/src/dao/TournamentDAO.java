package dao;

import entities.Sport;
import entities.Tournament;

import java.sql.SQLException;
import java.util.List;

public interface TournamentDAO {
    Tournament getTournamentById(int id) throws SQLException;

    List<Tournament> getAllTournaments() throws SQLException;

    List<Tournament> searchTournaments(String name, String sport, String city, String year) throws SQLException;
}
