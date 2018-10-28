package servlets.profile;

import entities.User;
import services.PostService;
import services.UserService;
import servlets.Helper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ProfileEditServlet")
public class ProfileEditServlet extends HttpServlet {
    private UserService userService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = new UserService();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userService.getCurrentUser(request) == null) {
            response.sendError(403);
        } else {
            int userId = userService.getCurrentUser(request).getId();
            String password = request.getParameter("editPassword");
            String name = request.getParameter("editName");
            String login = request.getParameter("editLogin");
            boolean success = userService.updateProfile(userId, password, name, login);
            if (success) {
                response.sendRedirect("/profile");
            } else {
                response.sendRedirect("/auth?msg=fail");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = userService.getCurrentUser(request).getId();
        User user = userService.getUserById(id);
        Map root = new HashMap<>();
        root.put("user", user);
        Helper.render(
                getServletContext(),
                response,
                "editprofile.ftl",
                root
        );
    }
}
