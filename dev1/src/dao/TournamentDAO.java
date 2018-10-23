package dao;

import entities.Sport;
import entities.Tournament;

import java.sql.SQLException;
import java.util.List;

public interface TournamentDAO {
    public Tournament getTournamentById(int id) throws SQLException;

    public List<Tournament> getAllTournaments() throws SQLException;

    public List<Tournament> getTournamentsByCity(String city) throws SQLException;

    public List<Tournament> getTournamentsByYear(String year) throws SQLException;

    public List<Tournament> getTournamentsBySport(Sport sport) throws SQLException;

    public Tournament getTournamentByName(String name) throws SQLException;
}
