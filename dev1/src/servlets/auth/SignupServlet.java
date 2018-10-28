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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "SignupServlet")
public class SignupServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userService.getCurrentUser(request) != null) {
            response.sendError(403);
        } else {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String name = request.getParameter("name");

            Matcher loginMatcher = Pattern.compile("[a-zA-Z][0-9a-zA-Z]{4,15}").matcher(login);
            if (!loginMatcher.matches()) {
                response.sendRedirect("/auth?msg=fail");
                return;
            }

            Matcher passwordMatcher = Pattern.compile("[0-9a-zA-Z!@#$%^&*()+-_=]{8,16}").matcher(password);
            if (!passwordMatcher.matches()) {
                response.sendRedirect("/auth?msg=fail");
                return;
            }

            User user = userService.signUp(login, password, name);
            userService.authorize(userService.getUserBeLogin(user.getLogin()), request);
            response.sendRedirect("/profile");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getCurrentUser(request);
        if (user != null) {
            response.sendRedirect("/profile");
            return;
        }
        Helper.render(
                getServletContext(),
                response,
                "signup.ftl",
                new HashMap<>()
        );
    }
}
