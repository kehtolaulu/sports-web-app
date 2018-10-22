package servlets.posts;

import entities.Post;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "PostEditServlet")
public class PostEditServlet extends HttpServlet {
    private UserService userService;
    private PostService postService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = new UserService();
        postService = new PostService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userService.getCurrentUser(request) == null) {
            response.sendError(403);
        } else {
            int id = getId(request);
            String text = request.getParameter("newText");
            String title = request.getParameter("newTitle");
            postService.updatePost(id, text, title);
            response.sendRedirect(String.format("/posts/%s", id));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userService.getCurrentUser(request) == null) {
            response.sendError(403);
        } else {
            Post post = postService.getPostById(getId(request));
            Map<String, Object> root = new HashMap<>() {{
                put("post", post);
            }};
            Helper.render(
                    getServletContext(),
                    response,
                    "editpost.ftl",
                    root
            );
        }
    }

    private int getId(HttpServletRequest request) {
        Pattern compile = Pattern.compile("/posts/([1-9][0-9]*)/edit");
        Matcher matcher = compile.matcher(request.getRequestURI());
        matcher.find();
        return Integer.parseInt(matcher.group(1));
    }
}
