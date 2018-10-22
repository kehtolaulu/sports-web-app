package servlets.auth;

import entities.User;
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

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
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
                    userService.addToken(current_user, response);
                }
                response.sendRedirect("/profile");
            } else {
                response.sendRedirect("/auth?err=too_bad_login");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getCurrentUser(request);
        if (user != null) {
            response.sendRedirect("/profile");
        } else {
            Helper.render(
                    getServletContext(),
                    response,
                    "login.ftl",
                    new HashMap<>()
            );
        }
    }
}
