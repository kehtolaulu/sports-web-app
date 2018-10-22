package servlets.results;

import entities.Tournament;
import services.TournamentService;
import servlets.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ResultsServlet")
public class ResultsServlet extends HttpServlet {

    private TournamentService tournamentService;

    @Override
    public void init() throws ServletException {
        tournamentService = new TournamentService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Tournament> tournaments = tournamentService.getAllTournaments();
        Map<String, Object> root = new HashMap<>();
        root.put("tournaments", tournaments);
        Helper.render(
                getServletContext(),
                response,
                "results.ftl",
                root
        );
    }
}
