package dao;

import entities.Tournament;

import javax.security.auth.callback.TextOutputCallback;
import java.sql.SQLException;
import java.util.List;

public interface TournamentDAO {
    public Tournament getTournamentById(int id) throws SQLException;
    public List<Tournament> getAllTournaments() throws SQLException;
}
