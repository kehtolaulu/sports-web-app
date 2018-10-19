package services;


import dao.MatchDAO;
import entities.Match;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MatchService {
    private MatchDAO matchDAO;

    public MatchService() {
        this.matchDAO = new dao.postgresdao.MatchDAO();
    }

    public List<Match> getAllMatches() {
        try {
            return matchDAO.getAllMatches();
        } catch (SQLException e) {
            return new LinkedList<>();
        }
    }
}
