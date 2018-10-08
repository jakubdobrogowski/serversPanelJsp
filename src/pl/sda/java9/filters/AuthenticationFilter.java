package pl.sda.java9.filters;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/editServer", "/editServer.jsp", "/saveServer", "/saveServer.jsp", "/showServerList"})
public class AuthenticationFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {


        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession(false);

        String id = (String) session.getAttribute("id");
        String user = (String) session.getAttribute("user");

        if (StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(user)){
            chain.doFilter(req, resp);

        } else {
            response.sendRedirect("/login.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
