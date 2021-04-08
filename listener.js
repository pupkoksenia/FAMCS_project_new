
function del(obj) {
    if (confirm('удалить пост')) {
        let id = obj.getAttribute("id");
        for (let i = 0; i < photoPosts.length; i++) {
            let el = photoPosts[i];
            if (el.id == id) {
                photoPosts.splice(i, 1);
              
                PP.saveToStorage();
                break;
            }
        }

        obj.parentNode.parentNode.remove();
        console.log(photoPosts.length + ' ' + localStorage.length);

    }
}


function showNewTenElements() {
  VV.showNew10El();
}

function deleteLike(nameWHOadd, i) {
    for (let k = 0; k < photoPosts[i].likes.length; k++) {
        if (photoPosts[i].likes[k] == nameWHOadd) {
            photoPosts[i].likes.splice(k,1);
            break;
        }
    }
}

function addLike(nameWHOadd, i) {
    photoPosts[i].likes.push(nameWHOadd);
}

function Like(obj) {

    let name = obj.parentNode.parentNode.children[0].children[1].innerHTML;
    let nameWHOadd = document.getElementsByClassName("name_surname")[0].innerHTML;


    for (let i = 0; i < photoPosts.length; i++) {
        if (photoPosts[i].author == name) {
            if (photoPosts[i].likes.includes(nameWHOadd)) {
                if (confirm('удалить лайк?')) {
                    deleteLike(nameWHOadd, i);
                }
            }
            else {
                if (confirm('добавить лайк?')) {
                    addLike(nameWHOadd, i);
                }
            }
            PP.saveToStorage();
            break;
        }
    }
}


 


