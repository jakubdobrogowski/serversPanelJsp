package pl.sda.java9.servlets;

import org.apache.commons.lang3.StringUtils;
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
import java.io.PrintWriter;

@WebServlet(name = "SaveServerServlet", urlPatterns = "/saveServer")
public class SaveServerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String host = request.getParameter("host");
        String port = request.getParameter("port");
        String owner = request.getParameter("owner");
        String status = request.getParameter("status");

        PrintWriter writer = response.getWriter();


        Integer id1 = null;
        if (StringUtils.isNumeric(id)) {

            id1 = Integer.valueOf(id);
        }

        Server server = new Server(id1, name, host, Integer.valueOf(port), Integer.valueOf(owner), status);

        if (id1 == null) {

            ServerDao.saveServer(server);
        } else {

            ServerDao.updateServer(server);
        }

        request.setAttribute("allUsers", CommonUtils.createUserMap(UserDao.getAllUsers()));
        request.setAttribute("allServers", ServerDao.getAllServers());
        RequestDispatcher rs = request.getRequestDispatcher("/serverList.jsp");
        writer.print("<font color=green>Server został zapisany pomyślnie</font>");
        rs.include(request, response);


    }

}

