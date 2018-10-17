package services;

import dao.postgresdao.PostgresSportsmanDAO;
import entities.Sportsman;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SportsmenService {
    private PostgresSportsmanDAO postgresSportsmanDAO;
    public SportsmenService() {
        postgresSportsmanDAO = new PostgresSportsmanDAO();
    }
    public List<Sportsman> getAllSportsmen() {
        try {
            return postgresSportsmanDAO.getAllSportsmen();
        } catch (SQLException e) {
            return new LinkedList<Sportsman>();
        }
    }
}
