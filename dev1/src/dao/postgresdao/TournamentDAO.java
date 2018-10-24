package dao.postgresdao;

import dao.SportDAO;
import entities.Sport;
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
    public List<Tournament> getTournamentsByCity(String city) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM tournament WHERE place = ?");
        statement.setString(1, city);
        ResultSet resultSet = statement.executeQuery();
        List<Tournament> tournaments = new ArrayList<>();
        while (resultSet.next()) {
            tournaments.add(instance(resultSet));
        }
        return tournaments;
    }

    @Override
    public List<Tournament> getTournamentsByYear(String year) throws SQLException {
        String date1 = year + "-01-01";
        String date2 = year + "-12-31";
        PreparedStatement statement = connection.prepareStatement("select * from table tournament where date_from between ? and ? OR date_to between ? and ?");
        statement.setDate(1, Date.valueOf(date1));
        statement.setDate(2, Date.valueOf(date2));
        ResultSet resultSet = statement.executeQuery();
        List<Tournament> tournaments = new ArrayList<>();
        while (resultSet.next()) {
            tournaments.add(instance(resultSet));
        }
        return tournaments;
    }

    @Override
    public List<Tournament> getTournamentsBySport(Sport sport) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from tournament where sport_id = ?");
        statement.setInt(1, sport.getId());
        ResultSet resultSet = statement.executeQuery();
        List<Tournament> tournaments = new ArrayList<>();
        while (resultSet.next()) {
            tournaments.add(instance(resultSet));
        }
        return tournaments;
    }

    @Override
    public Tournament getTournamentByName(String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from tournament where name = ?");
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return instance(resultSet);
        }
        return null;
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
                .filter(t -> year.equals("") || t.getYear().equals(year))
                .filter(t -> city.equals("") || t.getPlace().equals(city))
                .filter(t -> name.equals("") || t.getName().equals(name))
                .filter(t -> sport.equals("") || t.getSport().getName().equals(sport))
                .collect(Collectors.toList());
    }
}
