package services;

import dao.UserDAO;
import dao.postgresdao.PostgresUserDAO;
import entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class UserService {

    private UserDAO userDAO = new PostgresUserDAO();

    public UserService() {
        this.userDAO = new PostgresUserDAO();
    }

    public User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (User) session.getAttribute("current_user");
    }

    public User authenticate(HttpServletRequest request) {
        String login = request.getParameter("login");
        if (login != null) {
            User user;
            try {
                user = userDAO.getByLogin(login);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
            if (user == null) {
                return null;
            }
            String password = request.getParameter("password");
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void authorize(User current_user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("current_user", current_user);
    }
}
