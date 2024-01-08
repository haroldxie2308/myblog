<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up for Harold's Blog</title>
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<main class="container">
    <article class="grid">
        <div>
            <hgroup>
                <h1>Sign up</h1>
            </hgroup>
            <form action="${pageContext.request.contextPath}/UserServer?op=signup" method="post">
                <input
                        type="text"
                        name="username"
                        placeholder="username"
                        required
                />
                <input
                        type="password"
                        name="adgangskode"
                        placeholder="Password"
                        required
                />
                <button type="submit">Sign up</button>
            </form>
        </div>
    </article>
</main>

<jsp:include page="footer.jsp"></jsp:include>

</body>