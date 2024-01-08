<%--
  Created by IntelliJ IDEA.
  User: haroldxie
  Date: 2023/5/21
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="user" scope="session" class="com.haroldxie.myblog.Domain.User"/>
<jsp:useBean id="admin" scope="session" class="com.haroldxie.myblog.Domain.Admin"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
</head>
<body>
<header class="container">
    <nav>
        <a href="${pageContext.request.contextPath}/UserServer?op=index">
            <h2>Harold's Blog</h2>
        </a>
        <div>
            <ul class="list">
                <li>
                    <a href="${pageContext.request.contextPath}/UserServer?op=index">Home</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/UserServer?op=showAllBlogs">Posts</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/UserServer?op=showAllCategories">Categories</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/UserServer?op=showAllAuthors">Authors</a>
                </li>
                <c:if test="${user.user_id le 0}">
                    <c:if test="${admin.admin_id le 0}">
                        <li>
                            <a href="login.jsp">Login</a>
                        </li>
                        <li>
                            <a href="signup.jsp">Sign Up</a>
                        </li>
                    </c:if>
                </c:if>
                <c:if test="${admin.admin_id gt 0}">
                    <li>
                        <a href="${pageContext.request.contextPath}/AdminServer?op=siteManagement">Manage Site</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/AdminServer?op=logout">Log Out</a>
                    </li>
                </c:if>
                <c:if test="${user.user_id gt 0}">
                    <li>
                        <a href="${pageContext.request.contextPath}/UserServer?op=accountManagement">Manage your account</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/UserServer?op=logout">Log Out</a>
                    </li>
                </c:if>
            </ul>
        </div>
        <form action="${pageContext.request.contextPath}/UserServer?op=search" method="post">
            <input type="search" id="search" name="searchLine" placeholder="What are you looking for?">
        </form>
    </nav>
</header>
</body>
</html>
