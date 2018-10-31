package servlets.results;

import entities.Match;
import entities.Tournament;
import entities.User;
import services.MatchService;
import services.SportsmenService;
import services.TournamentService;
import services.UserService;
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
    private SportsmenService sportsmenService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        tournamentService = new TournamentService();
        matchService = new MatchService();
        sportsmenService = new SportsmenService();
        userService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Tournament tournament = tournamentService.getTournamentById(getId(request));
        List<Match> matches = matchService.getMatchesByTournament(tournament);
        User user = userService.getCurrentUser(request);
        for (Match m : matches) {
            m.setSportsmen(sportsmenService.getSportsmenByMatch(m));
        }
        Map<String, Object> root = new HashMap<>() {{
            put("tournament", tournament);
            put("matches", matches);
            put("user", user);
        }};
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
