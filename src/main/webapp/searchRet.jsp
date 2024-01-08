<%--
  Created by IntelliJ IDEA.
  User: haroldxie
  Date: 2023/5/24
  Time: 14:32
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
    <title>Search Result for ${searchFor}</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<main class="container">
    <h2>Blogs: </h2>
    <c:if test="${empty retBlogs}">
        <h3>Sorry, no blog content matches ${searchFor}</h3>
    </c:if>
    <c:if test="${!empty retBlogs}">
        <c:forEach items="${retBlogs}" var="blog" varStatus="vs">
            <article>
                <h3>${blog.title}</h3>
                <p>${blog.content}</p>
                <footer>
                    <h4>By ${blog.authorName}, Estimated Reading Time: ${blog.readingTime} Minute(s)</h4>
                    <a href="${pageContext.request.contextPath}/UserServer?op=seeBlogDetail&blog_id=${blog.blog_id}">Read more...    </a>
                </footer>
            </article>
        </c:forEach>
    </c:if>
    <h2>Users: </h2>
    <c:if test="${empty retUsers}">
        <h3>Sorry, no user's nickname matches ${searchFor}</h3>
    </c:if>
    <c:if test="${!empty retUsers}">
        <c:forEach items="${retUsers}" var="user" varStatus="vs">
            <article>
                <h3>Username: ${user.username} nickname: ${user.nickname}</h3>
                <p>Personal Statement: ${user.personalStatement}</p>
                <p>Account created on ${user.accountCreationDate}</p>
            </article>
        </c:forEach>
    </c:if>
</main>


<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
