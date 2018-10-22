package servlets.posts;

import entities.Post;
import entities.User;
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
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "PostsByAuthorServlet")
public class PostsByAuthorServlet extends HttpServlet {
    private PostService postService;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = new UserService();
        postService = new PostService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User author = userService.getUserById(getId(request));
        List<Post> posts = postService.getPostsByAuthor(author);
        Map<String, Object> root = new HashMap<>();
        root.put("posts", posts);
        root.put("author", author);
        Helper.render(
                getServletContext(),
                response,
                "postsbyauthor.ftl",
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
