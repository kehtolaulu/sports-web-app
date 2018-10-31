package dao.postgresdao;

import dao.SportDAO;
import entities.Tournament;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            return instance(resultSet);
        }
        return null;
    }

    private Tournament instance(ResultSet resultSet) throws SQLException {
        return new Tournament(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                sportDAO.getSportById(resultSet.getInt("sport_id")),
                resultSet.getDate("date_from"),
                resultSet.getDate("date_to"),
                resultSet.getString("place"),
                resultSet.getString("result"),
                resultSet.getString("year"));
    }

    @Override
    public List<Tournament> getAllTournaments() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM tournament");
        List<Tournament> tournaments = new ArrayList<>();
        while (resultSet.next()) {
            tournaments.add(instance(resultSet));
        }
        return tournaments;
    }

    @Override
    public List<Tournament> searchTournaments(String name, String sport, String city, String year) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM tournament");
        List<Tournament> tournaments = new ArrayList<>();
        while (rs.next()) {
            tournaments.add(instance(rs));
        }
        return tournaments
                .stream()
                .filter(t -> year.equals("") || t.getYear().toLowerCase().equals(year.toLowerCase()))
                .filter(t -> city.equals("") || t.getPlace().toLowerCase().equals(city.toLowerCase()))
                .filter(t -> name.equals("") || t.getName().toLowerCase().equals(name.toLowerCase()))
                .filter(t -> sport.equals("") || t.getSport().getName().toLowerCase().equals(sport.toLowerCase()))
                .collect(Collectors.toList());
    }
}
