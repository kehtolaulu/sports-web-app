package dao.postgresdao;

import dao.SportDAO;
import entities.Tournament;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournamentDAO implements dao.TournamentDAO {

    private Connection connection;
    private SportDAO sportDAO;

    public TournamentDAO() {

        this.connection = ConnectionSingleton.getInstance();
        sportDAO = new dao.postgresdao.SportDAO();
    }

    @Override
    public Tournament getTournamentById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM tournament WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Tournament(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    sportDAO.getSportById(resultSet.getInt("sport_id")),
                    resultSet.getDate("date_from"),
                    resultSet.getDate("date_to"),
                    resultSet.getString("place"),
                    resultSet.getString("result")
            );
        }
        return null;
    }

    @Override
    public List<Tournament> getAllTournaments() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM tournament");
        List<Tournament> tournaments = new ArrayList<>();
        while (resultSet.next()) {
            tournaments.add(new Tournament(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    sportDAO.getSportById(resultSet.getInt("sport_id")),
                    resultSet.getDate("date_from"),
                    resultSet.getDate("date_to"),
                    resultSet.getString("place"),
                    resultSet.getString("result")

            ));
        }
        return tournaments;
    }


}
