package servlets.results;

import entities.Sport;
import entities.User;
import services.MatchService;
import services.SportService;
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


@WebServlet(name = "SearchServlet")
public class SearchServlet extends HttpServlet {
    private MatchService matchService;
    private SportService sportService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        matchService = new MatchService();
        sportService = new SportService();
        userService = new UserService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Sport> sports = sportService.getAllSports();
        User user = userService.getCurrentUser(request);
        Helper.render(
                getServletContext(),
                response,
                "search.ftl",
                new HashMap<>() {{
                    put("sports", sports);
                    put("user", user);
                }}
        );

    }

}
