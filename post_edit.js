Load();

var user = location.search.split('user=')[1];
    user=decodeURI(user);



var id = location.search.split('guid=')[1];
var obj = null;

function Init() {
    if (id != undefined) {
        for (let i = 0; i < photoPosts.length; i++) {
            if (photoPosts[i].id == id) {
                obj = photoPosts[i];
                break;
            }
        }
        document.getElementById("model").value = obj.model;
        document.getElementById("country").value = obj.country;
        document.getElementById("displacement").value = obj.displacement;
        document.getElementById("price").value = obj.price;
        document.getElementById("date").value = obj.yearOfCreation;
        document.getElementById("hashtags").value = obj.hashtags;
        document.getElementById("photoLink").value = obj.photoLink;
    }
    else if (id == undefined) {
        obj = new Object();
        obj.createdAt=new Date();
         id = Math.floor(Math.random() * 100000);

        for (let i = 0; i < photoPosts.length; i++) {
            if (photoPosts[i].id == id) {
                id = Math.floor(Math.random() * 100000);
                break;
            }
        }

        obj.id = id;
        obj.author = document.getElementsByClassName("name_surname")[0].innerHTML;
        photoPostsSource.push(obj);
        photoPosts.push(obj);
        document.getElementById("hashtags").value= ArrOfHashtags;
        document.getElementById("model").value = ArrOfModels;
        document.getElementById("displacement").value = obj.displacement;
        document.getElementById("price").value = '200.000.000';
        document.getElementById("date").value = 2001;
        document.getElementById("photoLink").value = 'url link';
        }
}



function Save(ss) {

  
    obj.model = document.getElementById("model").value;
    obj.country = document.getElementById("country").value;
    obj.displacement = document.getElementById("displacement").value;
    obj.price = document.getElementById("price").value;
    obj.yearOfCreation = document.getElementById("date").value;
    obj.hashtags = document.getElementById("hashtags").value;
    obj.photoLink = document.getElementById("photoLink").value;
    obj.author=user;
    console.log(obj);

    PP.saveToStorage();
    alert('Пост успешно создан/изменен.')
}

Init();
