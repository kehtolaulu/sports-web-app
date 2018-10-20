package servlets.posts;

import entities.Post;
import entities.User;
import services.PostService;
import services.SportService;
import services.UserService;
import servlets.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "PostsServlet")
public class PostsServlet extends HttpServlet {
    private UserService userService;
    private PostService postService;
    private SportService sportService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
        postService = new PostService();
        sportService = new SportService();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userService.getCurrentUser(request) == null) {
            response.sendError(403);
        } else {
            String text = request.getParameter("text");
            String title = request.getParameter("title");
            int sport_id = (sportService.getSportByName(request.getParameter("sport"))).getId();
            try {
                postService.newPost(userService.getCurrentUser(request), title, text, sportService.getSportById(sport_id));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect("/profile");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getCurrentUser(request);
        List<Post> posts = postService.getAllPosts();
        Map<String, Object> root = new HashMap<>() {
            {
                put("user", user);
                put("posts", posts);
            }
        };
        Helper.render(
                getServletContext(),
                response,
                "posts.ftl",
                root
        );

    }
}
