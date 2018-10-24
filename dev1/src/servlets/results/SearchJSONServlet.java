package servlets.results;

import entities.Tournament;
import org.json.JSONArray;
import org.json.JSONObject;
import services.TournamentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchJSONServlet")
public class SearchJSONServlet extends HttpServlet {
    private TournamentService tournamentService;

    @Override
    public void init() throws ServletException {
        tournamentService = new TournamentService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONArray array = new JSONArray();
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        String year = request.getParameter("year");
        String sport = request.getParameter("sport");
        List<Tournament> tournaments = tournamentService.searchTournaments(name, sport, city, year);
        for (Tournament t : tournaments) {
            JSONObject tJson = new JSONObject();
//            JSONObject sJson = new JSONObject();
            tJson.put("tournament", t);
//            sJson.put("sport", t.getSport());
//            tJson.put("sport", sJson);
            array.put(tJson.toMap());
        }
        response.setContentType("text/json");
        response.getWriter().print(array.toString());
        response.getWriter().close();
    }
}
