package servlets;

import javafx.util.Pair;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet(name = "DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
    private List<Pair<Pattern, RequestDispatcher>> map;
    @Override
    public void init() {
        map = new LinkedList<>();

        addPattern("/index", "/index");
        addPattern("/sportsmen", "/sportsmen");
        addPattern("/results", "/results");
        addPattern("/tournaments/([1-9][0-9]*)", "/tournaments/:id");
        addPattern("/search", "/search");
        addPattern("/profile", "/profile");
        addPattern("/profile/([1-9][0-9]*)", "/profile/:id");
        addPattern("/posts", "/posts");
        addPattern("/posts/([1-9][0-9]*)/comments", "/posts/:id/comments");

        addPattern("/posts/([1-9][0-9]*)", "/posts/:id");
        addPattern("/comments/([1-9][0-9]*)", "/comments/:id");
        addPattern("/registration", "/registration");
        addPattern("/auth", "/auth");
        addPattern("/logout", "/logout");

    }
    private void addPattern(String regex, String dumbUrl) {
        map.add(new Pair<>(Pattern.compile(regex), getServletContext().getRequestDispatcher(dumbUrl)));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        for (var pair : map) {
            if (pair.getKey().matcher(uri).matches()) {
                pair.getValue().forward(req, resp);
                return;
            }
        }
        resp.sendError(404);
    }
}
