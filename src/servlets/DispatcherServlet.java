package servlets;

import servlets.posts.CommentServlet;
import servlets.posts.PostByIdServlet;
import servlets.posts.PostEditServlet;
import servlets.posts.PostsByAuthorServlet;
import servlets.profile.ProfileByIdServlet;
import servlets.profile.ProfileEditServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

@WebServlet(name = "DispatcherServlet")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Pattern, HttpServlet> dispatchMap = new TreeMap<>((o1, o2) -> o2.toString().compareTo(o1.toString())) {{
            put(Pattern.compile("/posts/([1-9][0-9]*)/comments"), new CommentServlet());
            put(Pattern.compile("/posts/([1-9][0-9]*)/edit"), new PostEditServlet());
            put(Pattern.compile("/posts/([1-9][0-9]*)"), new PostByIdServlet());
            put(Pattern.compile("/profile/([1-9][0-9]*)/posts"), new PostsByAuthorServlet());
            put(Pattern.compile("/profile/([1-9][0-9]*)/edit"), new ProfileEditServlet());
            put(Pattern.compile("/profile/([1-9][0-9]*)"), new ProfileByIdServlet());
        }};

        for (var pair : dispatchMap.entrySet()) {
            if (pair.getKey().matcher(req.getRequestURI()).matches()) {
                pair.getValue().init(getServletConfig());
                pair.getValue().service(req, resp);
                return;
            }
        }
    }
}
