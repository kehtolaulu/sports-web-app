package servlets.profile;

import services.UserService;
import servlets.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "ProfileServlet")
public class ProfileServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userService.getCurrentUser(request) == null) {
            response.sendError(403);
        } else {
            int userId = userService.getCurrentUser(request).getId();
            String password = request.getParameter("newPassword");
            String name = request.getParameter("newName");
            boolean success = userService.updateProfile(userId, password, name);
            if (success) {
                response.sendRedirect("/profile");
            } else {
                response.sendRedirect("/login?msg=fail");
            }
            response.sendRedirect("/profile");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        Helper.render(
                getServletContext(),
                response,
                "profile.ftl",
                new HashMap<>()
        );
    }
}
