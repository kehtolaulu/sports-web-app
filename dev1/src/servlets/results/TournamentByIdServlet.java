package servlets.results;

import entities.Tournament;
import entities.User;
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
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "TournamentByIdServlet")
public class TournamentByIdServlet extends HttpServlet {
    private TournamentService tournamentService;
    @Override
    public void init() throws ServletException {
        tournamentService = new TournamentService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Tournament tournament = tournamentService.getTournamentById(getId(request));
        Map<String, Object> root = new HashMap<>() {
            {
                put("tournament", tournament);
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
        Pattern compile = Pattern.compile("/posts/([1-9][0-9]*)");
        Matcher matcher = compile.matcher(request.getRequestURI());
        matcher.find();
        return Integer.parseInt(matcher.group(1));
    }
}
