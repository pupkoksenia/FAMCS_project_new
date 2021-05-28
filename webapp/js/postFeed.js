Init(0);

function Init(start) {

    let xhr = new XMLHttpRequest();
    let url = '/listPost?start=' + start
        + "&name=" + document.getElementById("name").value
        + "&model=" + document.getElementById("model").value
        + "&country=" + document.getElementById("country").value
        + "&displacement=" + document.getElementById("displacement").value
        + "&price=" + document.getElementById("price").value;

    xhr.open('GET', url,
        true);
    xhr.send();

    xhr.onreadystatechange = function () { // (3)
        if (xhr.readyState != 4) return;

        if (xhr.status != 200) {
            alert(xhr.status + ': ' + xhr.statusText);
        } else {

            let lst = JSON.parse(xhr.responseText);
            console.log(lst);
            let tmp = '';
            for (let i = 0; i < lst.length; i++) {
                tmp += view(lst[i]);
            }
            if (start == 0) document.getElementsByClassName("right-list")[0].innerHTML = tmp;
            else {
                document.getElementsByClassName("right-list")[0].innerHTML += tmp;
                let node=document.getElementsByClassName("loading")[0];
                node.style.visibility = 'hidden';
            }
        }
    }


}

function LogOut() {
    if (confirm("Закончить работу?") == false) return;
    let xhr = new XMLHttpRequest();
    xhr.open('GET', '/logout', false);
    xhr.send();
    window.location.reload();

}


function view(post) {
    console.log(post);
    let dt = new Date(post.createdAt);
    let tmp = '<div class="item">' +
        ' <div class="item-top">' +
        ' <img src="right_images/People_icon.png">' +
        '<span class="fio">' + post.name + '</span>' +
        '<span class="date">' + dt.getDay() + '/' + (dt.getMonth() + 1) + '/' + dt.getFullYear() + '</span>' +
        '</div>' +
        '<div class="item-middle">' +
        '<div class="photo">' +
        '<img src="' + post.photoLink + '">' +
        '</div>' +
        '<div class="text">' +
        '<div class="p">модель</div>' +
        '<div class="i">' + post.model + '</div>' +

        '<div class="p">страна</div>' +
        '<div class="i">' + post.country + '</div>' +

        '<div class="p">водоизмещение</div>' +
        '<div class="i">' + post.displacement + '</div>' +

        '<div class="p">цена</div>' +
        '<div class="i">' + post.price + '</div>' +

        '<div class="p">год создания</div>' +
        '<div class="i">' + post.yearOfCreation + '</div>' +
        '</div>' +

        '<div class="discription">' + post.description;

    let tmp1 = '';
    /*for (let k of post.hashtags) {
        tmp1 += k;
    }*/


    tmp += '</div>' +
        '<div class="hash"  style="grid-area: hash">' + tmp1 + '</div>' +
        '</div>' +
        '<div class="item-bottom">' +
        '<img src="right_images/message_icon.png">' +
        '<img src="right_images/refresh-icon.png" class="item_send" onclick="Send(this)">';

    let el = document.getElementsByClassName("name_surname")[0];

    if (userId >0) {

        tmp += '<img src="right_images/Heart-'+post.lk+'.png" ship=' + post.id + '  class="item_like" onclick="Like(this)">' ;
    }
    if (userId == post.fkuser) {

        tmp += '<img src="right_images/delete.png" id=' + post.id + ' class="item_del" onclick="del(this)">' +
            '<a href="editPost.jsp?id=' + post.id + '"><img src="right_images/ed.png"></a>';
    }
    tmp +=
        '</div>' +
        '</div>';
    return tmp;
}

function SortingClear() {
    document.getElementById("name").value = '';
    document.getElementById("model").value = '';
    document.getElementById("country").value = '';
    document.getElementById("displacement").value = '';
    document.getElementById("price").value = '';
    document.getElementById("hashtags").value = '';

    Init(0);
}


function del(obj) {
    if (confirm('удалить пост')) {
        let id = obj.getAttribute("id");
        console.log(id);
        let xhr = new XMLHttpRequest();
        let url = '/tweets?id=' + id;
        //console.log(url);
        xhr.open('DELETE', url, true);
        xhr.send();

        xhr.onreadystatechange = function () { // (3)
            if (xhr.readyState != 4) return;

            if (xhr.status != 200) {
                alert(xhr.status + ': ' + xhr.statusText);
            } else {
                //  console.log(xhr.responseText);
                obj.parentNode.parentNode.remove();
            }
        }


        //   console.log(photoPosts.length + ' ' + localStorage.length);


    }
}

function Send(obj) {
    let nameWHOadd = document.getElementsByClassName("name_surname")[0].innerHTML;
    if (nameWHOadd === "") window.location = "/signIn.jsp";
    else window.location = "/sendEmail.jsp";
}

function Like(obj) {

    if (userId == 0) window.location = "/signIn.jsp";
    else {
        let xhr = new XMLHttpRequest();
        let url = "/like?id=" + obj.getAttribute("ship");
        console.log(url);
        xhr.open('POST', url, false);
        xhr.send();

        if (xhr.status != 200) {
            alert(xhr.status + ': ' + xhr.statusText);
        } else {
            console.log("res="+xhr.responseText);

            if (xhr.responseText == "unlogged") {
                alert("Вы не зарегестрированы");
                window.location = "/signIn.jsp"
            }

            obj.setAttribute("src", "/right_images/Heart-" + xhr.responseText + ".png");
        }
    }


}


