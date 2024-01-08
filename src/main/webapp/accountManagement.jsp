<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.haroldxie.myblog.Utils.JDBCHelper.*"%>
<%@ page import="com.haroldxie.myblog.Web.UserServer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="user" scope="session" class="com.haroldxie.myblog.Domain.User"/>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
    <title>Manage Your Account</title>
</head>
<body>

    <jsp:include page="header.jsp"></jsp:include>

    <main class="container">
        <c:forEach items="${blogsByUserID}" var="blog" varStatus="vs">
            <article>
                <h3>${blog.title}</h3>
                <p>${blog.content}</p>
                <footer>
                    <h4>Estimated Reading Time: ${blog.readingTime} Minute(s)</h4>
                    <a href="${pageContext.request.contextPath}/UserServer?op=seeBlogDetail&blog_id=${blog.blog_id}">Read more...    </a>
                    OR click here to <a href="${pageContext.request.contextPath}/UserServer?op=deleteBlog&blog_id=${blog.blog_id}">delete</a> this blog.
                </footer>
            </article>
        </c:forEach>

        <h2>You can update your personal information below.</h2>
        <form action="${pageContext.request.contextPath}/UserServer?op=updateUserInfo" method="post" id="updateUserInfo-container">
            <input type="text" name="nickname" placeholder="Your nickname here">
            <input type="password" name="adgangskode" placeholder="New password">
            <input type="text" name="emailAddr" placeholder="Email">
            <input type="text" name="personalStatement" placeholder="Tell me about yourself">
<%--            <input type="date" name="birthday">--%>
            <button type="submit">Update</button>
        </form>
    </main>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>