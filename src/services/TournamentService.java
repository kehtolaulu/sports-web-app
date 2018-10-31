package services;

import dao.TournamentDAO;
import entities.Sport;
import entities.Tournament;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TournamentService {
    private TournamentDAO tournamentDAO;

    public TournamentService() {
        this.tournamentDAO = new dao.postgresdao.TournamentDAO();
    }

    public List<Tournament> getAllTournaments() {
        try {
            return tournamentDAO.getAllTournaments();
        } catch (SQLException e) {
            return new LinkedList<>();
        }
    }

    public Tournament getTournamentById(int id) {
        try {
            return tournamentDAO.getTournamentById(id);
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Tournament> searchTournaments(String name, String sport, String city, String year) {
        try {
            return tournamentDAO.searchTournaments(name, sport, city, year);
        } catch (SQLException e) {
            return new LinkedList<>();
        }
    }

}

