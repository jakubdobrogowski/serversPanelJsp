package pl.sda.java9.servlets;



import pl.sda.java9.database.daos.ServerDao;
import pl.sda.java9.database.daos.UserDao;
import pl.sda.java9.domains.Server;
import pl.sda.java9.utils.CommonUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServerListServlet", urlPatterns = "/showServerList")
public class ServerListServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Server> allServers = ServerDao.getAllServers();
        request.setAttribute("allServers", allServers);

        request.setAttribute("allUsers", CommonUtils.createUserMap(UserDao.getAllUsers()));

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/serverList.jsp");
        requestDispatcher.forward(request, response);
    }
}
