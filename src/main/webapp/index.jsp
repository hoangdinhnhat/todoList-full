<%@page import="com.hdn.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Todo</title>
        <link rel="stylesheet" href="./css/base.css">
        <link rel="stylesheet" href="./css/index.css">
        <link rel="stylesheet" href="./css/style.css">
        <link href="https://cdn.jsdelivr.net/gh/hung1001/font-awesome-pro-v6@44659d9/css/all.min.css" rel="stylesheet" type="text/css" />
        <script defer type="module" src="./js/script.js"></script>
    </head>
    <body>
        <%
            User user = (User)session.getAttribute("user");
        %>
        <section class="navbar">
            <div class="user" onclick="showUserFunc(this)">
                <i class="fa-light fa-circle-user"></i>
                <div class="profile">
                    <ul class="user-function">
                        <a class="item" href="profile">Your profile</a>
                        <li class="item">Log Out</li>
                    </ul>
                </div>
            </div>
        </section>
        <%
            if(user == null)
            {
                %>
                <section class="log-layer"></section>
                <%
            }
         %>
        <section class="todoapp">
        </section>

        <script src="./js/sign-templete.js"></script>
    </body>
</html>
