package pl.sda.java9.servlets;

import pl.sda.java9.database.daos.ServerDao;
import pl.sda.java9.database.daos.UserDao;
import pl.sda.java9.domains.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();

        String user = request.getParameter("user");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        User userByLogin = UserDao.getUserByLogin(user);

        if (userByLogin.getLogin() == null) {

            writer.print("<font color=red>Użytkownik o podanym adresie email nie istnieje</font>");
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.include(request, response);

            return;
        }


        if (!userByLogin.getPassword().equals(password)) {

            writer.print("<font color=red>Hasło nieprawidłowe</font>");
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.include(request, response);

            return;
        }

        request.setAttribute("allUsers", createUserMap(UserDao.getAllUsers()));
        request.setAttribute("allServers", ServerDao.getAllServers());

        session.setAttribute("user", userByLogin.getLogin());
        session.setAttribute("id", userByLogin.getId().toString());
        session.setAttribute("isAdmin", userByLogin.getIsAdmin().toString());
        session.setMaxInactiveInterval(15 * 60);

        RequestDispatcher rs = request.getRequestDispatcher("/serverList.jsp");
        rs.forward(request, response);
    }

    public static HashMap<Integer, User> createUserMap(List<User> userList) {

        HashMap<Integer, User> userMap = new HashMap<>();

        for (User user : userList) {

            userMap.put(user.getId(), user);
        }

        return userMap;
    }

}