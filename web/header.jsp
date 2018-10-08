<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String user = (String) session.getAttribute("user");
    if (StringUtils.isNotEmpty(user)) {
%>
<p>Zalogowany jako: <%=user%></p>
<%
    }
%>
