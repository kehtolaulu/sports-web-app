package dao.postgresdao;

import entities.Match;
import entities.Sportsman;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class SportsmanDAO implements dao.SportsmanDAO {
    private TeamDAO teamDAO;
    private Connection connection;

    public SportsmanDAO() {
        this.connection = ConnectionSingleton.getInstance();
        teamDAO = new TeamDAO();
    }


    @Override
    public List<Sportsman> getAllSportsmen() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM sportsman");

        LinkedList<Sportsman> sportsmen = new LinkedList<>();
        while (resultSet.next()) {
            Sportsman sportsman = instance(resultSet);
            sportsmen.add(sportsman);
        }
        return sportsmen;
    }

    private Sportsman instance(ResultSet resultSet) throws SQLException {
        return new Sportsman(
                resultSet.getInt("id"),
                teamDAO.getTeamById(resultSet.getInt("team_id")),
                resultSet.getString("name"),
                resultSet.getString("bio"),
                resultSet.getString("photo")
        );
    }

    @Override
    public List<Sportsman> getSportsmenByMatch(Match match) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM sportsman INNER JOIN match_to_sportsman m on sportsman.id = m.sportsman_id WHERE match_id = ?");
        statement.setInt(1, match.getId());
        LinkedList<Sportsman> sportsmen = new LinkedList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Sportsman sportsman = instance(resultSet);
            sportsmen.add(sportsman);
        }
        return sportsmen;
    }

}
