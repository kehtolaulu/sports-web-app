package servlets.profile;

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

@WebServlet(name = "ProfileServlet")
public class ProfileServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        User user = userService.getCurrentUser(request);
        if (user == null) {
            response.sendRedirect("/auth");
        } else {
            int id = userService.getCurrentUser(request).getId();
            User user1 = userService.getUserById(id);
            Map root = new HashMap<>();
            root.put("user", user1);
            Helper.render(
                    getServletContext(),
                    response,
                    "myprofile.ftl",
                    root
            );
        }
    }
}
