<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 001 01.05.21
  Time: 8:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>post_edit</title>
    <link rel="stylesheet" href="/css/editPost.css">
    <script src='/js/editPost.js'></script>
</head>

<body>

<div class="bigboss">
    <div class="header" style="grid-area: header">

        <img src="header_images/image_icon.svg" style="float:left; margin:0px 0px 0px 5px;">

        <a href="/postFeed.jsp" class="main">Posts</a>



    </div>
    <div class="middle" style="grid-area: middle">
        <div class="middle-a">


            <span class="bl"><label for="photolink">Picture</label><input type="text" id="photolink"></span>
            <span class="bl"><label for="model">Model</label><input type="text" id="model"></span>
            <span class="bl"><label for="description">Description</label><input type="text" id="description"></span>
            <span class="bl"><label for="country">Country</label><input type="text" id="country"></span>
            <span class="bl"><label for="displacement">Displacement</label><input type="number"
                                                                                  id="displacement"></span>
            <span class="bl"><label for="price">Price</label><input type="text" id="price"></span>
            <span class="bl"><label for="date">Date</label><input type="text" id="date"></span>

            <!-- <span class="bl"><label for="date">Photo ship link</label><input type="text" id="photoLink"></span>-->
            <span class="bl"><input type="button" value="Сохранить пост" onclick="Save(this)"></span>





        </div>
    </div>

    <div class="footer" style="grid-area: footer">Корабли Ксения Пупко 2 курс 9 группа pupkoksenia@gmail.com 25.05.2021</div>
</div>
</body>

</html>
