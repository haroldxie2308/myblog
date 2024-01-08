<%--
  Created by IntelliJ IDEA.
  User: haroldxie
  Date: 2023/5/23
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
    <title>Authors</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<main class="container">
    <c:forEach items="${allAuthors}" var="author" varStatus="vs">
        <article>
            <h3>Author's name: ${author.username} nickname: ${author.nickname}</h3>
            <p>Personal Statement: ${author.personalStatement}</p>
            <p>Account created on ${author.accountCreationDate}</p>
            <footer>
                <a href="${pageContext.request.contextPath}/UserServer?op=getBlogsByAuthor&author_id=${author.user_id}">See all posts by this author.</a>
            </footer>
        </article>
    </c:forEach>
</main>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>