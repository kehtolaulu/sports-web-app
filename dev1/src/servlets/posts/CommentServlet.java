package servlets.posts;

import entities.Comment;
import org.json.JSONArray;
import org.json.JSONObject;
import services.CommentService;
import services.PostService;
import services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "CommentServlet")
public class CommentServlet extends HttpServlet {
    private UserService userService;
    private CommentService commentService;
    private PostService postService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = new UserService();
        commentService = new CommentService();
        postService = new PostService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Comment> comments = commentService.getCommentsByPost(postService.getPostById(getId(request)));
        JSONArray array = new JSONArray();
        String query = request.getParameter("query");
        for (Comment comment : comments) {
            JSONObject matchJson = new JSONObject();
            matchJson.put("comment", comment);
            array.put(matchJson.toMap());
        }
        response.setContentType("text/json");
        response.getWriter().print(array.toString());
        response.getWriter().close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userService.getCurrentUser(request) == null) {
            response.sendError(403);
        } else {
            String text = request.getParameter("text");
            int post_id = getId(request);
            try {
                Comment comment = commentService.newComment(userService.getCurrentUser(request), postService.getPostById(post_id), text);
                JSONObject commentJson = new JSONObject();
                commentJson.put("comment", comment);
                response.getWriter().print(commentJson);
                response.getWriter().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private int getId(HttpServletRequest request) {
        Pattern compile = Pattern.compile("/posts/([1-9][0-9]*)/comments");
        Matcher matcher = compile.matcher(request.getRequestURI());
        matcher.find();
        return Integer.parseInt(matcher.group(1));
    }
}
