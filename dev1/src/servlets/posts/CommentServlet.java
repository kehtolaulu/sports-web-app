package servlets.posts;

import entities.Comment;
import services.CommentService;
import services.PostService;
import services.SportService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "CommentServlet")
public class CommentServlet extends HttpServlet {
    private UserService userService;
    private CommentService commentService;
    private PostService postService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
        commentService = new CommentService();
        postService = new PostService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userService.getCurrentUser(request) == null) {
            response.sendError(403);
        } else {
            String text = request.getParameter("text");
            int post_id = getId(request);
            try {
                commentService.newComment(userService.getCurrentUser(request), postService.getPostById(post_id), text);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect("/profile");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = getId(request);
        commentService.deleteComment(id);
        response.sendRedirect("/posts/" + id);
    }


    private int getId(HttpServletRequest request) {
        Pattern compile = Pattern.compile("/posts/([1-9][0-9]*)/comments");
        Matcher matcher = compile.matcher(request.getRequestURI());
        matcher.find();
        return Integer.parseInt(matcher.group(1));
    }
}
