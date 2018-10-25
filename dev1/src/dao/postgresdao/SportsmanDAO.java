package dao.postgresdao;

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
    public Sportsman getSportsmanById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM sportsman WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return instance(resultSet);
        }
        return null;
    }
}
