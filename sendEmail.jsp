<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 001 01.05.21
  Time: 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<% boolean isLogin=(request.getSession().getAttribute("name")!=null);%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>send_email</title>
    <link rel="stylesheet" href="/css/sendEmail.css">
</head>
<body>

<div class="bigboss">
    <div class="header" style="grid-area: header">

        <img src="header_images/image_icon.svg" style="float:left; margin:0px 0px 0px 5px;">
        <span class="name_surname"><%=(isLogin?request.getSession().getAttribute("name")+"<a href='/editPost.jsp'>add Post</a>":"") %></span>
        <a href="postFeed.jsp" class="posts">Posts</a>

        <a href="signIn.jsp" class="log_out">Log out</a>


    </div>
    <div class="middle" style="grid-area: middle;border-radius: 15px">
        <div class="middle-a">
            <div class="gmail">Gmail</div>
            <input type="text" placeholder="Введите адрес электронной почты"></input>
            <div></div>
            <input type="submit" value="send"></input>
        </div>

    </div>

    <div class="footer" style="grid-area: footer"> ©2020-2021</div>
</div>

</body>
</html>
