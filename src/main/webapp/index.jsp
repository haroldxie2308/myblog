<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.haroldxie.myblog.Utils.JDBCHelper.*"%>
<%--<%@ page import="org.apache.catalina.User" %>--%>
<%@ page import="com.haroldxie.myblog.Web.UserServer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Harold's Blog</title>
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
</head>
<body>

    <jsp:include page="header.jsp"></jsp:include>
<main class="container">
    <c:if test="${sessionScope.user.user_id le 0}">
        <c:if test="${sessionScope.admin.admin_id le 0}">
            <figure>
                <table>
                    <tbody>
                    <tr>
                        <td><strong>HEY!</strong></td>
                        <td><em><a href="${pageContext.request.contextPath}/UserServer?op=toLogin">Sign in to CREATE A BLOG NOW!</a></em></td>
                    </tr>
                    </tbody>
                </table>
            </figure>
        </c:if>
    </c:if>
    <c:if test="${sessionScope.user.user_id gt 0}">
        <figure>
            <table>
                <tbody>
                <tr>
                    <td><strong>HEY!</strong></td>
                    <td><em><a href="${pageContext.request.contextPath}/UserServer?op=createNewBlog" class="headline-description" >${sessionScope.user.username}, CREATE A BLOG NOW!</a></em></td>
                </tr>
                </tbody>
            </table>
        </figure>
    </c:if>
    <c:if test="${sessionScope.admin.admin_id gt 0}">
        <figure>
            <table>
                <tbody>
                <tr>
                    <td><strong>HEY!</strong></td>
                    <td><em>Have a nice day, admin ${sessionScope.admin.adminName} !</em></td>
                </tr>
                </tbody>
            </table>
        </figure>
    </c:if>
    <h2>Feeling Lucky? Why not see the following posts?</h2>
    <c:forEach items="${sessionScope.blogs}" var="blog" varStatus="vs">
        <article>
            <h3>${blog.title}</h3>
            <p>${blog.content}</p>
            <footer>
                <h4>By ${blog.authorName}, Estimated Reading Time: ${blog.readingTime} Minute(s)</h4>
                <a href="${pageContext.request.contextPath}/UserServer?op=seeBlogDetail&blog_id=${blog.blog_id}">Read more...</a>
            </footer>
        </article>
    </c:forEach>
</main>
</body>
<jsp:include page="footer.jsp"></jsp:include>

</html>