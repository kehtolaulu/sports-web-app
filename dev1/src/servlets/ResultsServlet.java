package servlets;

import entities.Match;
import services.MatchService;
import services.SportsmenService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ResultsServlet")
public class ResultsServlet extends HttpServlet {

    private MatchService matchService;
    @Override
    public void init() throws ServletException {
        matchService = new MatchService();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Match> matches = matchService.getAllMatches();
        Map<String, Object> root = new HashMap<>();
        root.put("results", matches);
        Helper.render(
                getServletContext(),
                response,
                "results.ftl",
                new HashMap<>()
        );
    }
}
