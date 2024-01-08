<%--
  Created by IntelliJ IDEA.
  User: haroldxie
  Date: 2023/5/21
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
    <title>Redirecting...</title>
</head>
<body>
    <main class="container">
    &nbsp;&nbsp;&nbsp;&nbsp;<h2>${message}</h2>
        <h4>Redirects to main page in 3s, if not correctly redirected, please click <a href="${pageContext.request.contextPath}/UserServer?op=index">HERE</a> </h4>
        <%
            response.setHeader("refresh", "3;URL=" + request.getContextPath() + "/index.jsp");
        %>
    </main>
</body>
<jsp:include page="footer.jsp"></jsp:include>

</html>
