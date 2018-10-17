package servlets;

import entities.User;
import services.SportsmenService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "SportsmenServlet")
public class SportsmenServlet extends HttpServlet {

    private UserService userService;
    private SportsmenService sportsmenService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
        sportsmenService = new SportsmenService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getCurrentUser(request);
        Map<String, Object> root = new HashMap<>() {{
            put("user", user);
        }};
        Helper.render(
                getServletContext(),
                response,
                "sportsmen.ftl",
                root
        );
    }
}
