<%@page import="com.hdn.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="profile/profile.css">
    <link href="https://cdn.jsdelivr.net/gh/hung1001/font-awesome-pro-v6@44659d9/css/all.min.css" rel="stylesheet"
        type="text/css" />
    <script defer src="profile/profile.js"></script>
</head>

<body>
    <%
        User user = (User)session.getAttribute("user");
        if(user == null) response.sendRedirect("/ToDoList");
    %>
    <section class="navbar">
        <div class="home-btn">
            <a href="/ToDoList-Maven"><i class="fa-regular fa-house-user"></i></a>
        </div>
        <div class="user" onclick="showUserFunc(this)">
            <i class="fa-light fa-circle-user"></i>
            <div class="profile">
                <ul class="user-function">
                    <a class="item" href="profile">Your profile</a>
                    <a class="item" href="log-out">Log Out</a>
                </ul>
            </div>
        </div>
    </section>

    <div class="profile-info">
        <div class="changeAvatar"
            style="background-image: url(https://wallpaperaccess.com/full/1111994.jpg);">
            <form action="storage" target="_blank" method="post" enctype="multipart/form-data">
                <label for="uploadFile" class="uploadBtn"><i class="fa-duotone fa-camera"></i></label>
                <input type="file" name="imgFile" id="uploadFile">
            </form>
        </div>
        <div class="layerChangeImg">
            <div class="containImg"
                style="background-image: url();">
            </div>
            <div class="confirm">CONFIRM</div>
        </div>
        <div class="userInfor">
            <h1 style="text-align: center;">PROFILE</h1>
            <table>
                <tr>
                    <th>Username: </th>
                    <td>
                        <%=user.getUsername() %>
                    </td>
                </tr>

                <tr>
                    <th>Password: </th>
                    <td>
                        <%= user.getPassword() %>
                    </td>
                </tr>

                <tr>
                    <th>Email: </th>
                    <td>
                        <%=user.getEmail() %>
                    </td>
                </tr>
            </table>
        </div>
    </div>

    <script src="js/sign-templete.js"></script>
</body>

</html>