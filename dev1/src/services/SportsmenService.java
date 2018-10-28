package services;

import dao.SportsmanDAO;
import entities.Match;
import entities.Sportsman;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SportsmenService {
    private SportsmanDAO postgresSportsmanDAO;

    public SportsmenService() {
        postgresSportsmanDAO = new dao.postgresdao.SportsmanDAO();
    }

    public List<Sportsman> getAllSportsmen() {
        try {
            return postgresSportsmanDAO.getAllSportsmen();
        } catch (SQLException e) {
            return new LinkedList<Sportsman>();
        }
    }

    public List<Sportsman> getSportsmenByMatch(Match match) {
        try {
            return postgresSportsmanDAO.getSportsmenByMatch(match);
        } catch (SQLException e) {
            return new LinkedList<>();
        }
    }

}
