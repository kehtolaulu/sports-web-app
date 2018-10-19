package dao.postgresdao;

import dao.SportDAO;
import dao.TeamDAO;
import dao.TournamentDAO;
import entities.Match;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MatchDAO implements dao.MatchDAO {
    private Connection connection;
    private SportDAO sportDAO;
    private TournamentDAO tournamentDAO;
    private TeamDAO teamDAO;

    public MatchDAO() {
        this.connection = ConnectionSingleton.getInstance();
        this.sportDAO = new dao.postgresdao.SportDAO();
        this.tournamentDAO = new dao.postgresdao.TournamentDAO();
        this.teamDAO = new dao.postgresdao.TeamDAO();
    }
    @Override
    public List<Match> getAllMatches() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM posts");
        List<Match> matches = new ArrayList<>();
        while (resultSet.next()) {
            matches.add(new Match(
                    resultSet.getInt("id"),
                    resultSet.getDate("datetime"),
                    resultSet.getString("result"),
                    sportDAO.getSportById(resultSet.getInt("sport_id")),
                    tournamentDAO.getTournamentById(resultSet.getInt("tournament_id")),
                    teamDAO.getTeamById(resultSet.getInt("team_1_id")),
                    teamDAO.getTeamById(resultSet.getInt("team_2_id"))
            ));
        }
        return matches;
    }
}
