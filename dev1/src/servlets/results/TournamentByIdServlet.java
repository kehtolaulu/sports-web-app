package servlets.results;

import entities.Match;
import entities.Tournament;
import services.MatchService;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "TournamentByIdServlet")
public class TournamentByIdServlet extends HttpServlet {
    private TournamentService tournamentService;
    private MatchService matchService;

    @Override
    public void init() throws ServletException {
        tournamentService = new TournamentService();
        matchService = new MatchService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Tournament tournament = tournamentService.getTournamentById(getId(request));
        List<Match> matches = matchService.getMatchesByTournament(tournament);
        Map<String, Object> root = new HashMap<>() {
            {
                put("tournament", tournament);
                put("matches", matches);
            }
        };
        Helper.render(
                getServletContext(),
                response,
                "tournament.ftl",
                root
        );
    }

    private int getId(HttpServletRequest request) {
        Pattern compile = Pattern.compile("/tournament/([1-9][0-9]*)");
        Matcher matcher = compile.matcher(request.getRequestURI());
        matcher.find();
        return Integer.parseInt(matcher.group(1));
    }
}
