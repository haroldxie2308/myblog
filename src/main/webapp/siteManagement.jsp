<%--
  Created by IntelliJ IDEA.
  User: haroldxie
  Date: 2023/5/23
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--<jsp:useBean id="user" scope="session" class="com.haroldxie.myblog.Domain.User"/>--%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
    <title>Manage Site</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<main class="container">
    <c:forEach items="${allBlogs}" var="blog" varStatus="vs">
        <article>
            <h3>${blog.title}</h3>
            <p>${blog.content}</p>
            <footer>
                <h4>By ${blog.authorName}, Estimated Reading Time: ${blog.readingTime} Minute(s)</h4>
                <a href="${pageContext.request.contextPath}/UserServer?op=seeBlogDetail&blog_id=${blog.blog_id}">Read more...    </a>
                OR click here to <a href="${pageContext.request.contextPath}/UserServer?op=deleteBlog&blog_id=${blog.blog_id}">delete</a> this blog.
            </footer>
        </article>
    </c:forEach>
    <c:forEach items="${allUsers}" var="user" varStatus="vs">
        <article>
            <h3>User ID: ${user.user_id} name: ${user.username} nickname: ${user.nickname}</h3>
            <h3>Password: ${user.adgangskode}</h3>
            <p>Personal Statement: ${user.personalStatement}</p>
            <p>Account created on ${user.accountCreationDate}</p>
        </article>
        Click here to <a href="${pageContext.request.contextPath}/AdminServer?op=deleteUser&user_id=${user.user_id}">DELETE</a> this user
        Here to edit user info:
        <form action="${pageContext.request.contextPath}/AdminServer?op=updateUserInfo&user_id=${user.user_id}" method="post">
            <input type="text" name="username" placeholder="New username here">
            <input type="password" name="adgangskode" placeholder="New password">
            <input type="text" name="personalStatement" placeholder="New Personal Statement">
                <%--            Birthday: <input type="date">--%>
            <button type="submit">Update</button>
        </form>
    </c:forEach>
</main>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>