package servlets.posts;

import entities.Comment;
import entities.Post;
import entities.User;
import services.CommentService;
import services.PostService;
import services.UserService;
import servlets.Helper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "PostByIdServlet", urlPatterns = {"/posts/:id"})
public class PostByIdServlet extends HttpServlet {
    private UserService userService;
    private PostService postService;
    private CommentService commentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = new UserService();
        postService = new PostService();
        commentService = new CommentService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = getId(request);
        try {
            postService.deletePost(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            User user = userService.getCurrentUser(request);
            int id = getId(request);
            Post post;
            post = postService.getPostById(id);
            if (post == null) {
                response.sendError(404);
                return;
            }
            List<Comment> comments = commentService.getCommentsByPost(post);
            Map<String, Object> root = new HashMap<>();
            root.put("post", post);
//            root.put("can_delete", user.equals(post.getAuthor()));
            root.put("comments", comments);
            root.put("user", user);
            Helper.render(
                    getServletContext(),
                    response,
                    "post.ftl",
                    root
            );
    }

    private int getId(HttpServletRequest request) {
        Pattern compile = Pattern.compile("/posts/([1-9][0-9]*)");
        Matcher matcher = compile.matcher(request.getRequestURI());
        matcher.find();
        return Integer.parseInt(matcher.group(1));
    }
}
