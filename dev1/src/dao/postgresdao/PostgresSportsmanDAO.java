package dao.postgresdao;

import dao.SportsmanDAO;
import entities.Sport;
import entities.Sportsman;
import entities.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class PostgresSportsmanDAO implements SportsmanDAO {
    private PostgresTeamDAO teamDAO;
    private Connection connection;

    public PostgresSportsmanDAO() {
        this.connection = ConnectionSingleton.getInstance();
        teamDAO = new PostgresTeamDAO();
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
