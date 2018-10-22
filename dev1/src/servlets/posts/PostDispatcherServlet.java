package servlets.posts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

@WebServlet(name = "PostDispatcherServlet")
public class PostDispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Pattern, HttpServlet> dispatchMap = new TreeMap<>((o1, o2) -> o2.toString().compareTo(o1.toString())) {{
            put(Pattern.compile("/posts/([1-9][0-9]*)/comments"), new CommentServlet());
            put(Pattern.compile("/posts/([1-9][0-9]*)"), new PostByIdServlet());
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
