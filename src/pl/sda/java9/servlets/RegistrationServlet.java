package pl.sda.java9.servlets;

import pl.sda.java9.database.daos.UserDao;
import pl.sda.java9.domains.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        PrintWriter writer = response.getWriter();

        String user = request.getParameter("user"); // to jest login aka email
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        User user1 = createUser(user, password, name, surname);

        request.setAttribute("user", user1);

        User userByLogin = UserDao.getUserByLogin(user);
        if (userByLogin.getLogin() != null) {

            writer.print("<font color=red>Użytkownik o podanym adresie email istnieje</font>");
            RequestDispatcher rd = request.getRequestDispatcher("/registration.jsp");
            rd.include(request, response);
            return;
        }

        UserDao.saveUser(user1);
        request.getRequestDispatcher("/registrationSuccess.jsp").forward(request, response);
    }

    private User createUser(String user, String password, String name, String surname) {

        User user1 = new User();
        user1.setName(name);
        user1.setSurname(surname);
        user1.setLogin(user);
        user1.setPassword(password);
        return user1;
    }
}

