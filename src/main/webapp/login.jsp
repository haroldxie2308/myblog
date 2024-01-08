<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login to Harold's Blog</title>
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
    <main class="container">
        <article class="grid">
            <div>
                <hgroup>
                    <h1>Sign in as User</h1>
                </hgroup>
                <form action="${pageContext.request.contextPath}/UserServer?op=login" method="post" class="form">
                    <input
                            type="text"
                            name="username"
                            placeholder="username"
                            aria-label="Login"
                            autocomplete="username"
                            required
                    />
                    <input
                            type="password"
                            name="adgangskode"
                            placeholder="Password"
                            aria-label="Password"
                            autocomplete="current-password"
                            required
                    />
                    <button type="submit">Login</button>
                </form>
            </div>
        </article>
        <article class="grid">
            <div>
                <hgroup>
                    <h1>Sign in as Admin</h1>
                </hgroup>
                <form action="${pageContext.request.contextPath}/AdminServer?op=login" method="post" class="form">
                    <input
                            type="text"
                            name="adminName"
                            placeholder="adminName"
                            aria-label="Login"
                            required
                    />
                    <input
                            type="password"
                            name="adgangskode"
                            placeholder="Password"
                            aria-label="Password"
                            required
                    />
                    <button type="submit">Login</button>
                </form>
            </div>
        </article>
    </main>

    <jsp:include page="footer.jsp"></jsp:include>
</body>