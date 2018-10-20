package servlets.posts;

import entities.Post;
import entities.User;
import services.PostService;
import services.UserService;
import servlets.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "PostByIdServlet")
public class PostByIdServlet extends HttpServlet {
    private UserService userService;
    private PostService postService;

    @Override
    public void init() throws ServletException {
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

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = getId(request);
        try {
            postService.deletePost(id);
            response.sendRedirect("/posts");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = userService.getCurrentUser(request);
        if (currentUser == null) {
            response.sendRedirect("/login");
        } else {
            int id = getId(request);
            Post post = null;
            post = postService.getPostById(id);
            if (post == null) {
                response.sendError(404);
                return;
            }
            Map<String, Object> root = new HashMap<>();
            root.put("post", post);
            root.put("can_delete", currentUser.equals(post.getAuthor()));
            root.put("can_edit", currentUser.equals(post.getAuthor()));
            Helper.render(
                    getServletContext(),
                    response,
                    "post.ftl",
                    root
            );
        }

    }
    private int getId(HttpServletRequest request) {
        Pattern compile = Pattern.compile("/posts/([1-9][0-9]*)");
        Matcher matcher = compile.matcher(request.getRequestURI());
        matcher.find();
        return Integer.parseInt(matcher.group(1));
    }
}