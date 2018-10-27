package app.dao.postgresdao;

import entities.Sport;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class SportDAO implements app.dao.SportDAO {
    private Connection connection;

    public SportDAO() {
        this.connection = ConnectionSingleton.getInstance();
    }

    @Override
    public Sport getSportById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM sport WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return instance(resultSet);
        }
        return null;
    }

    private Sport instance(ResultSet resultSet) throws SQLException {
        return new Sport(
                resultSet.getInt("id"),
                resultSet.getString("name")
        );
    }

    @Override
    public Sport getSportByName(String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM sport WHERE name = ?");
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return instance(resultSet);
        }
        return null;
    }

    @Override
    public List<Sport> getAllSports() throws SQLException {
        List<Sport> sports = new LinkedList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM sport");
        while (resultSet.next()) {
            sports.add(instance(resultSet));
        }
        return sports;
    }


}
