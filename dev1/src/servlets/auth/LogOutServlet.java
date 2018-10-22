package servlets.auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogOutServlet")
public class LogOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        deleteCookie(request, response);
        response.sendRedirect("/auth");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private void deleteCookie(HttpServletRequest request, HttpServletResponse response) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("remember_me")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }
}
