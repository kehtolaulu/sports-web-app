package dao.postgresdao;

import entities.Sport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SportDAO implements dao.SportDAO {
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
            return new Sport(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
            );
        }
        return null;
    }

    @Override
    public Sport getSportByName(String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM sport WHERE name = ?");
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Sport(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
            );
        }
        return null;
    }


}
