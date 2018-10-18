package services;

import dao.postgresdao.UserDAO;
import entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {

    private dao.UserDAO userDAO = new UserDAO();

    public UserService() {
        this.userDAO = new UserDAO();
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

    public boolean signUp (String login, String password, String name) {
        if (!isIdentifier(login)) {
            return false;
        }
        if (!isPassword(password)) {
            return false;
        }
        String hash = hashPassword(password);
        User user = new User(-1, login, hash, name);
        try {
            userDAO.addUser(user);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    private String hashPassword(String password) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(salt);


        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        return new String(hashedPassword, StandardCharsets.US_ASCII);
    }

    private boolean isPassword(String password) {
        Matcher matcher = Pattern.compile("[0-9a-zA-Z!@#$%^&*()+-_=]{8,16}").matcher(password);
        return matcher.matches();
    }

    private boolean isIdentifier(String login) {
        Matcher matcher = Pattern.compile("[a-zA-Z][0-9a-zA-Z]{4,15}").matcher(login);
        return matcher.matches();
    }
}
