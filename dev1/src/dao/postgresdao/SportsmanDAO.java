package dao.postgresdao;

import entities.Sportsman;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            Sportsman sportsman = new Sportsman(
                    resultSet.getInt("id"),
                    teamDAO.getTeamById(resultSet.getInt("team_id")),
                    resultSet.getString("name"),
                    resultSet.getString("bio")
            );
            sportsmen.add(sportsman);
        }
        return sportsmen;
    }
}
