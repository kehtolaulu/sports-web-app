package dao.postgresdao;

import entities.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamDAO implements dao.TeamDAO {
    private Connection connection;
    private SportDAO sportDAO;

    public TeamDAO() {
        this.connection = ConnectionSingleton.getInstance();
        sportDAO = new SportDAO();
    }

    @Override
    public Team getTeamById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM team WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Team(
                    resultSet.getInt("id"),
                    sportDAO.getSportById(resultSet.getInt("sport_id")),
                    resultSet.getString("name")
            );
        }
        return null;
    }
}
