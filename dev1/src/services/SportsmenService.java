package services;

import dao.postgresdao.SportsmanDAO;
import entities.Sportsman;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SportsmenService {
    private SportsmanDAO postgresSportsmanDAO;
    public SportsmenService() {
        postgresSportsmanDAO = new SportsmanDAO();
    }
    public List<Sportsman> getAllSportsmen() {
        try {
            return postgresSportsmanDAO.getAllSportsmen();
        } catch (SQLException e) {
            return new LinkedList<Sportsman>();
        }
    }
}
