package services;

import dao.UserDAO;
import entities.User;

import javax.servlet.http.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new dao.postgresdao.UserDAO();
    }

    public User getCurrentUser(HttpServletRequest req) {
        User currentUser = (User) req.getSession().getAttribute("current_user");
        if (currentUser != null) {
            return currentUser;
        }

        if (req.getCookies() == null || req.getCookies().length == 0) {
            return null;
        }

        Optional<Cookie> token = Arrays.stream(req.getCookies())
                .filter(c -> c.getName().equals("remember_me"))
                .findAny();
        if (token.isPresent()) {
            User userByToken = null;
            try {
                userByToken = userDAO.getUserByToken(token.get().getValue());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return userByToken;
        }
        return null;
    }


    /**
     * checks login, password -> user instance
     *
     * @param request
     * @return User
     */
    public User authenticate(HttpServletRequest request) {
        String login = request.getParameter("login");
        if (login != null) {
            User user;
            try {
                user = userDAO.getUserByLogin(login);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
            if (user == null) {
                return null;
            }
            String password = hashPassword(request.getParameter("password"));
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

    public boolean signUp(String login, String password, String name) {
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

    public void addToken(User user, HttpServletResponse response) {
        String token = createToken(user.getLogin());
        response.addCookie(new Cookie("remember_me", token));
        try {
            userDAO.addToken(user, token);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateProfile(int id, String password, String name, String login) {
        String hash = "";
        if (password == "") {
            try {
                hash = userDAO.getUserById(id).getPassword();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            if (!isPassword(password)) {
                return false;
            }
            hash = hashPassword(password);
        }
        try {
            userDAO.updateUser(id, hash, name, login);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public User getUserById(int id) {
        try {
            return userDAO.getUserById(id);
        } catch (SQLException e) {
            return null;
        }
    }

    private String createToken(String username) {
        String token = username + new Date().toString();
        return hashPassword(token);
    }

    private String hashPassword(String password) {
        final byte[] salt = new byte[]{-26, 107, -28, 36, 90, -64, -119, 70, -80, 115, -84, -38, -19, -123, -88, -70};

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(salt);

        byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(hash);
    }

    private boolean isPassword(String password) {
        Matcher matcher = Pattern.compile("[0-9a-zA-Z!@#$%^&*()+-_=]{8,16}").matcher(password);
        return matcher.matches();
    }

    private boolean isIdentifier(String login) {
        Matcher matcher = Pattern.compile("[a-zA-Z][0-9a-zA-Z]{4,15}").matcher(login);
        return matcher.matches();
    }

    public void updateUserPicture(InputStream input, User user) throws IOException {
//        final String ROOT = "\\kehtolaulu\\IdeaProjects\\sports-web-app\\out\\artifacts\\sports_web_app_war_exploded\\";
        File file = new File("/Users/kehtolaulu/sports-web-app/dev1/out/artifacts/sports_web_app_war_exploded/static/images/" + user.getLogin() + ".jpg");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream output = new FileOutputStream(file, false);

        byte[] bytes = new byte[512];

        int count = input.read(bytes);
        while (count != -1) {
            output.write(bytes);
            count = input.read(bytes);
        }
        input.close();
        output.close();
    }

}
