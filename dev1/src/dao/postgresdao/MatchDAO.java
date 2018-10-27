package app.dao.postgresdao;

import app.dao.SportDAO;
import app.dao.SportsmanDAO;
import app.dao.TeamDAO;
import app.dao.TournamentDAO;
import entities.Match;
import entities.Tournament;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchDAO implements app.dao.MatchDAO {
    private Connection connection;
    private SportDAO sportDAO;
    private TournamentDAO tournamentDAO;
    private TeamDAO teamDAO;
    private SportsmanDAO sportsmanDAO;

    public MatchDAO() {
        this.connection = ConnectionSingleton.getInstance();
        this.sportDAO = new app.dao.postgresdao.SportDAO();
        this.tournamentDAO = new app.dao.postgresdao.TournamentDAO();
        this.teamDAO = new app.dao.postgresdao.TeamDAO();
        this.sportsmanDAO = new app.dao.postgresdao.SportsmanDAO();
    }

    @Override
    public List<Match> getAllMatches() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs1 = statement.executeQuery("SELECT * FROM match INNER JOIN match_to_teams ON match.id = match_to_teams.match_id");
        List<Match> matches = new ArrayList<>();
        while (rs1.next()) {
            matches.add(teamsInstance(rs1));
        }
        ResultSet rs2 = statement.executeQuery("SELECT * FROM match INNER JOIN match_to_sportsman ON match.id = match_to_sportsman.match_id");
        while (rs2.next()) {
            matches.add(sportsmanInstance(rs2));
        }
        return matches;
    }

    @Override
    public List<Match> getMatchesByTournament(Tournament tournament) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM match INNER JOIN match_to_teams ON match.id = match_to_teams.match_id WHERE tournament_id = ?");
        statement.setInt(1, tournament.getId());
        ResultSet resultSet = statement.executeQuery();
        List<Match> matches = new ArrayList<>();
        while (resultSet.next()) {
            matches.add(teamsInstance(resultSet));
        }
        PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM match INNER JOIN match_to_sportsman ON match.id = match_to_sportsman.match_id WHERE tournament_id = ?");
        statement2.setInt(1, tournament.getId());
        ResultSet rs2 = statement2.executeQuery();
        while (rs2.next()) {
            matches.add(sportsmanInstance(rs2));
        }
        return matches;
    }

    private Match teamsInstance(ResultSet resultSet) throws SQLException {
        return new Match(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getDate("datetime"),
                resultSet.getString("result"),
                sportDAO.getSportById(resultSet.getInt("sport_id")),
                tournamentDAO.getTournamentById(resultSet.getInt("tournament_id")),
                teamDAO.getTeamById(resultSet.getInt("team_1_id")),
                teamDAO.getTeamById(resultSet.getInt("team_2_id"))
        );
    }

    private Match sportsmanInstance(ResultSet rs2) throws SQLException {
        return new Match(
                rs2.getInt("id"),
                rs2.getString("name"),
                rs2.getDate("datetime"),
                rs2.getString("result"),
                sportDAO.getSportById(rs2.getInt("sport_id")),
                tournamentDAO.getTournamentById(rs2.getInt("tournament_id")),
                sportsmanDAO.getSportsmanById(rs2.getInt("sportsman_id"))
        );
    }

}
