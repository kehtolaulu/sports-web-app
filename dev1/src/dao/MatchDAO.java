package dao;

import entities.Match;
import entities.Tournament;

import java.sql.SQLException;
import java.util.List;

public interface MatchDAO {
    List<Match> getAllMatches() throws SQLException;
    List<Match> getMatchesByTournament(Tournament tournament) throws SQLException;
}
