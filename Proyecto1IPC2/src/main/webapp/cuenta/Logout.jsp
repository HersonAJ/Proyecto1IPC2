<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    
    if (session != null) {
        session.invalidate();
    }
    response.sendRedirect(request.getContextPath() + "/index.jsp"); 
%>
