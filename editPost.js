
let id = location.search.split('id=')[1];
Init();
console.log("isOk");
function Init() {
    console.log(id);
    if (id == undefined) {
        id = 0;
        return;
    }

    let xhr = new XMLHttpRequest();
    let url = '/tweets?id=' + id;
    xhr.open('GET', url, true);
    xhr.send();

    xhr.onreadystatechange = function () { // (3)
        if (xhr.readyState != 4) return;

        if (xhr.status != 200) {
            alert(xhr.status + ': ' + xhr.statusText);
        } else {
            console.log(xhr.responseText);
            let obj = JSON.parse(xhr.responseText);
            document.getElementById("model").value = obj.model;
            document.getElementById("country").value = obj.country;
            document.getElementById("description").value = obj.description;
            document.getElementById("displacement").value = obj.displacement;
            document.getElementById("price").value = obj.price;
            document.getElementById("date").value = obj.yearOfCreation;
            document.getElementById("hashtags").value = obj.hashtags;
            document.getElementById("photolink").value = obj.photoLink;
        }
    }

}


function Save(ss) {
    let xhr = new XMLHttpRequest();
    let url='/tweets?id=' + id
        //+"&name="+document.getElementById("name").value
        +"&model="+document.getElementById("model").value
        +"&country="+document.getElementById("country").value
        +"&displacement="+document.getElementById("displacement").value
        +"&price="+document.getElementById("price").value
        +"&yearOfCreation="+document.getElementById("date").value
         +"&photoLink="+document.getElementById("photolink").value
        +"&description="+document.getElementById("description").value
    ;
    console.log(url);
    xhr.open('POST', url,true);
    xhr.send();

    xhr.onreadystatechange = function () { // (3)
        if (xhr.readyState != 4) return;

        if (xhr.status != 200) {
            alert(xhr.status + ': ' + xhr.statusText);
        } else {
            console.log(xhr.responseText);
            if (id == 0) alert("новый пост создан");
            else {
                alert("пост обновлен");
            }
            //window.location = '/postFeed.jsp';
        }
    }

}



