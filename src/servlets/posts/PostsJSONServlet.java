package servlets.posts;

import com.google.gson.Gson;
import services.PostService;
import services.SportService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PostsJSONServlet")
public class PostsJSONServlet extends HttpServlet {
    private PostService postService;
    private SportService sportService;

    @Override
    public void init() throws ServletException {
        postService = new PostService();
        sportService = new SportService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sport = request.getParameter("sport");
        Object[] posts = postService.getPostsBySport(sportService.getSportByName(sport)).toArray();
        Gson gson = new Gson();
        String json = gson.toJson(posts);
        response.setContentType("text/json");
        response.getWriter().print(json);
        response.getWriter().close();
    }
}
