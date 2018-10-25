package servlets.results;

import com.google.gson.Gson;
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
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        String year = request.getParameter("year");
        String sport = request.getParameter("sport");
        Object[] tournaments = tournamentService.searchTournaments(name, sport, city, year).toArray();
        Gson gson = new Gson();
        String json = gson.toJson(tournaments);
        response.setContentType("text/json");
        response.getWriter().print(json);
        response.getWriter().close();
    }
}
