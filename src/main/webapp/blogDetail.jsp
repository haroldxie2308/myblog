<%--
  Created by IntelliJ IDEA.
  User: haroldxie
  Date: 2023/5/22
  Time: 16:35
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
        <article>
            <h2>${blog.title}</h2>
            <h3>By ${blog.authorName} </h3>
            <p>${blog.content}</p>
        </article>
    </main>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
