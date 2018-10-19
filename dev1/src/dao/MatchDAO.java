package dao;

import entities.Match;

import java.sql.SQLException;
import java.util.List;

public interface MatchDAO {
    List<Match> getAllMatches() throws SQLException;
}
