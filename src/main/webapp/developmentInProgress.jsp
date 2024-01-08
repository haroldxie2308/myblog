<%--
  Created by IntelliJ IDEA.
  User: haroldxie
  Date: 2023/5/24
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
    <title>${blog.title}</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<main class="container">
    <section id="loading">
        <h2>This page is under development, please come back later</h2>
        <article aria-busy="true"></article>
        <a href="${pageContext.request.contextPath}/UserServer?op=index">Click here to go back to main page.</a>
    </section>
</main>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
