<%--
  Created by IntelliJ IDEA.
  User: haroldxie
  Date: 2023/5/21
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.haroldxie.myblog.Utils.JDBCHelper.*"%>
<%@ page import="com.haroldxie.myblog.Web.UserServer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create your own blog post</title>
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <main class="container">
        <h2>Create your own blog here ~ </h2>
        <form action="${pageContext.request.contextPath}/UserServer?op=saveNewBlog" method="post" class="form">
            <input type="text" name="title" placeholder="Your title here">
            <input type="text" name="readingTime" placeholder="Estimated Reading Time">
    <%--        <input type="image" name="pic">--%>
            <textarea rows="10" cols="80" name="content" placeholder="Your blog content here."></textarea>
            <button type="reset" class="btn form-btn">Clear</button>
            <button type="submit" class="btn form-btn">Submit</button>
        </form>
    </main>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>