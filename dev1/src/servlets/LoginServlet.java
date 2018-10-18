package servlets;

import entities.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userService.getCurrentUser(request) != null) {
            response.sendError(403);
        } else {
            User current_user = userService.authenticate(request);
            if (current_user != null) {
                userService.authorize(current_user, request);
                if (request.getParameter("remember_me") != null) {
                    // TODO: 17/10/2018  
                    response.addCookie(
                            new Cookie("remember_me", "token" + current_user.getLogin())
                    );
                }
                response.sendRedirect("/profile");
            } else {
                response.sendRedirect("/login?err=too_bad_login");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getCurrentUser(request);
        Map<String, Object> root = new HashMap<>() {{
            put("user", user);
        }};
        Helper.render(
                getServletContext(),
                response,
                "login.ftl",
                root
        );
    }
}
