package dao.postgresdao;

import dao.SportDAO;
import entities.Sport;
import entities.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgresSportDAO implements SportDAO {
    private Connection connection;
    public PostgresSportDAO() {
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
}
