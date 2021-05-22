<%@ page import="org.apache.http.protocol.ResponseServer" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 001 01.05.21
  Time: 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<% boolean isLogin=(request.getSession().getAttribute("name")!=null);%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>post_feed</title>
    <link rel="stylesheet" href="/css/postFeed.css">
</head>

<body>
<script>let userId=<%=(isLogin?request.getSession().getAttribute("id"):0)%>;
</script>
<div class="bigboss">
    <div class="header" style="grid-area: header" id='top'>
        <img src="header_images/image_icon.svg" style="float:left; margin:0px 0px 0px 5px;">
        <span class="name_surname"><%=(isLogin?request.getSession().getAttribute("name")+"<a href='/editPost.jsp'>add Post</a>":"") %></span>
        <span class="log"><%=(isLogin?"<span onclick='LogOut()'>Log Out</span>":"<a href='/signIn.jsp'>Log In</a>") %></span>


    </div>
    <div class="left" style="grid-area: left">


        <legend style="margin: 5px 5px 5px 3px;"></legendstyle>Filter:</legend>
        <span class="bl"><label for="name">Name</label><input type="text" id="name"></span>
        <span class="bl"><label for="model">Model</label><input type="text" id="model"></span>
        <span class="bl"><label for="country">Country</label><input type="text" id="country"></span>
        <span class="bl"><label for="displacement">Displacement</label><input type="number" id="displacement"></span>
        <span class="bl"><label for="price">Price</label><input type="number" id="price"></span>
        <span class="bl"><label for="hashtags">Hashtags</label><input type="text" id="hashtags"></span>

        <span class="bl"><input type="submit" value="Отправить" onclick="Init(0)"></span>
        <span class="bl"><input type="submit" value="Очистить" onclick="SortingClear()"></span>
    </div>




    <div class="right" style="grid-area: right">
        <div class="right-list-block">

            <div class="right-list">

            </div>
        </div>
        <div class="loading" onclick="Init(11)"><span>Load more...</span> </div>







    </div>


    <div class="footer" style="grid-area: footer"> ©2020-2021</div>
</div>




<script src="/js/postFeed.js"></script>

</body>

</html>