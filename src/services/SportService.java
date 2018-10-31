package services;

import dao.SportDAO;
import entities.Sport;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SportService {
    private SportDAO sportDAO;

    public SportService() {
        this.sportDAO = new dao.postgresdao.SportDAO();
    }

    public Sport getSportByName(String name) {
        try {
            return sportDAO.getSportByName(name);
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Sport> getAllSports() {
        try {
            return sportDAO.getAllSports();
        } catch (SQLException e) {
            return new LinkedList<>();
        }
    }
}
