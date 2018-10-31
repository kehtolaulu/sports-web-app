package servlets.results;

import com.google.gson.Gson;
import entities.Tournament;
import entities.User;
import org.json.JSONArray;
import org.json.JSONObject;
import services.SportService;
import services.TournamentService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AutocompleteServlet")
public class AutocompleteServlet extends HttpServlet {

    private TournamentService tournamentService;

    @Override
    public void init() throws ServletException {
        tournamentService = new TournamentService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Tournament> tournaments = tournamentService.getAllTournaments();
        String query = request.getParameter("query");
        List<String> names = new ArrayList<>();
        for (Tournament t : tournaments) {
            if (t.getName().toLowerCase().contains(query.toLowerCase())) {
                names.add(t.getName());
            }
        }
        Gson gson = new Gson();
        String json = gson.toJson(names);
        response.setContentType("text/json");
        response.getWriter().print(json);
        response.getWriter().close();
    }
}
