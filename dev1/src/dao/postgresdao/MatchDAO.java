package dao.postgresdao;

import dao.SportDAO;
import dao.SportsmanDAO;
import dao.TeamDAO;
import dao.TournamentDAO;
import entities.Match;
import entities.Tournament;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MatchDAO implements dao.MatchDAO {
    private Connection connection;
    private SportDAO sportDAO;
    private TournamentDAO tournamentDAO;
    private TeamDAO teamDAO;
    private SportsmanDAO sportsmanDAO;

    public MatchDAO() {
        this.connection = ConnectionSingleton.getInstance();
        this.sportDAO = new dao.postgresdao.SportDAO();
        this.tournamentDAO = new dao.postgresdao.TournamentDAO();
        this.teamDAO = new dao.postgresdao.TeamDAO();
        this.sportsmanDAO = new dao.postgresdao.SportsmanDAO();
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

        PreparedStatement statement2 = connection.prepareStatement("SELECT DISTINCT id, name, datetime, result, sport_id, tournament_id FROM match INNER JOIN match_to_sportsman ON match.id = match_to_sportsman.match_id WHERE tournament_id = ?");
        statement2.setInt(1, tournament.getId());
        ResultSet rs2 = statement2.executeQuery();
        while (rs2.next()) {
            matches.add(sportsmanInstance(rs2));
        }
        return matches.stream().distinct().collect(Collectors.toList());
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
        Match match = new Match();
        match.setId(rs2.getInt("id"));
        match.setName(rs2.getString("name"));
        match.setDatetime(rs2.getDate("datetime"));
        match.setResult(rs2.getString("result"));
        match.setSport(sportDAO.getSportById(rs2.getInt("sport_id")));
        match.setTournament(tournamentDAO.getTournamentById(rs2.getInt("tournament_id")));
        return match;
    }

}
