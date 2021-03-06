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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "ProfileByIdServlet")
public class ProfileByIdServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getUserById(getId(request));
        User current = userService.getCurrentUser(request);
        Map<String, Object> root = new HashMap<>() {
            {
                put("user", user);
                put("current", current);
            }
        };
        Helper.render(
                getServletContext(),
                response,
                "profile.ftl",
                root
        );

    }

    private int getId(HttpServletRequest request) {
        Pattern compile = Pattern.compile("/profile/([1-9][0-9]*)");
        Matcher matcher = compile.matcher(request.getRequestURI());
        matcher.find();
        return Integer.parseInt(matcher.group(1));
    }
}
