package servlets.posts;

import services.CommentService;
import services.PostService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "CommentByIdServlet")
public class CommentByIdServlet extends HttpServlet {
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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = getId(request);
        commentService.deleteComment(id);
    }

    private int getId(HttpServletRequest request) {
        Pattern compile = Pattern.compile("/comments/([1-9][0-9]*)");
        Matcher matcher = compile.matcher(request.getRequestURI());
        matcher.find();
        return Integer.parseInt(matcher.group(1));
    }
}
