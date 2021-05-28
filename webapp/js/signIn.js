function login() {
    let xhr = new XMLHttpRequest();

    let url = '/users?login=' + document.getElementById("login").value
        + "&password=" + document.getElementById("password").value;
    console.log(url);
    xhr.open('POST', url, true);
    xhr.send();

    xhr.onreadystatechange = function () { // (3)
        if (xhr.readyState != 4) return;
        if (xhr.status == 404) {
            window.location="/error.jsp";
            return;
        }
        if (xhr.status == 401) {
            alert("not correct password");
            return;
        }
        if (xhr.status != 200) {
            alert(xhr.status + ': ' + xhr.statusText);
        } else {
           // alert("ok");
            window.location="/postFeed.jsp";
        }
    }


}