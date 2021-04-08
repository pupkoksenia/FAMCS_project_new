
let VV = new View();


class PostList {

    constructor(ArrOfHashtags, ArrOfNames) {

        this.ArrayOfHashtags = new Array(ArrOfHashtags);
        this.ArrayOfNames = new Array(ArrOfNames);


    }
  

    saveToStorage() {
        localStorage.clear();
        for (let i = 0; i < photoPosts.length; i++) {
            let key =i;// photoPosts[i].id;
            var serialItem = JSON.stringify(photoPosts[i]);
            localStorage.setItem(key, serialItem);
        }
    }


}

