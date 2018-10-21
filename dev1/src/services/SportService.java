package services;

import dao.SportDAO;
import entities.Sport;

import java.sql.SQLException;

public class SportService {
    private SportDAO sportDAO;

    public SportService() {
        this.sportDAO = new dao.postgresdao.SportDAO();
    }

    public Sport getSportById(int id) {
        try {
            return sportDAO.getSportById(id);
        } catch (SQLException e) {
            return null;
        }
    }

    public Sport getSportByName(String name) {
        try {
            return sportDAO.getSportByName(name);
        } catch (SQLException e) {
            return null;
        }
    }
}
