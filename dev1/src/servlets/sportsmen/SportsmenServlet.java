package servlets.sportsmen;

import entities.Sportsman;
import entities.User;
import services.SportsmenService;
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

@WebServlet(name = "SportsmenServlet")
public class SportsmenServlet extends HttpServlet {

    private UserService userService;
    private SportsmenService sportsmenService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
        sportsmenService = new SportsmenService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getCurrentUser(request);
        List<Sportsman> sportsmen = sportsmenService.getAllSportsmen();
        Map<String, Object> root = new HashMap<>() {{
            put("user", user);
            put("sportsmen", sportsmen);
        }};
        Helper.render(
                getServletContext(),
                response,
                "sportsmen.ftl",
                root
        );
    }
}
