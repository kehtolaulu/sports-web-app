package servlets.results;

import entities.Match;
import services.MatchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "SearchJSONServlet")
public class SearchJSONServlet extends HttpServlet {
    private MatchService matchService;

    @Override
    public void init() throws ServletException {
        matchService = new MatchService();
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Match> matches = matchService.getAllMatches();
//        JSONArray array = new JSONArray();
//        String query = request.getParameter("query");
//        for (Match match : matches) {
//            if ((match.getTeam1().getName().toLowerCase().contains(query.toLowerCase())) ||
//                    (match.getSportsman().getName().toLowerCase().contains(query.toLowerCase())) ||
//                    (match.getTeam2().getName().toLowerCase().contains(query.toLowerCase()))) {
//                JSONObject matchJson = new JSONObject();
//                matchJson.put("match", match);
//                array.put(matchJson.toMap());
//            }
//        }
//        response.setContentType("text/json");
//        response.getWriter().print(array.toString());
//        response.getWriter().close();
//    }
}
