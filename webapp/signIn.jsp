<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 001 01.05.21
  Time: 8:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>sign_in</title>
    <link rel="stylesheet" href="/css/signIn.css">
    <script src="/js/signIn.js"></script>
</head>
<body>

<div class="bigboss">
    <div class="header" style="grid-area: header"> </div>
    <div class="middle" style="grid-area: middle;border-radius: 15px;">
        <div class="middle-a">


            <div class="log">Login</div>
            <input type="text" placeholder="Введите логин" id="login" value="Pypkin"></input>
            <div class="pass">Password</div>
            <input type="text" placeholder="Введите пароль" id="password" value="Pypkin"></input>
            <div></div>
            <input type="submit" value="send" onclick="login()" id="submit"></input>
            <div></div>
            <div class="sn">
                <img class="gmail" src="right_images/Gmail-icon.png" >
                <img class="facebook" src="right_images/social-facebook-button-blue-icon.png" >
                <img class="vk" src="right_images/Vk-icon needed.png" >
            </div>


        </div>

    </div>

    <div class="footer" style="grid-area: footer"> ©2020-2021</div>
</div>

</body>

</html>