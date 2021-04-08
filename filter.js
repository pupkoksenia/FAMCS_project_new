function iteration(phHashtags, filterHashtags) {

    let s = 0;
    for (let k of filterHashtags) {
        if (phHashtags.includes(k)) s++;
    }
    if (s === phHashtags.length) return 1;
    return 0;
}


function Sorting(tt) {

    var filterConfig = new Object();
    if (document.getElementById("name").value.length > 0) filterConfig.author = document.getElementById("name").value;
    if (document.getElementById("model").value.length > 0) filterConfig.model = document.getElementById("model").value;
    if (document.getElementById("country").value.length > 0) filterConfig.country = document.getElementById("country").value;
    if (document.getElementById("displacement").value.length > 0) filterConfig.displacement = document.getElementById("displacement").value;
    if (document.getElementById("price").value.length > 0) filterConfig.price = document.getElementById("price").value;
    if (document.getElementById("hashtags").value.length > 0) filterConfig.hashtags = document.getElementById("hashtags").value;


    let result = [];


    let kkey = Object.keys(filterConfig);
    //console.log(kkey);
    //console.log(filterConfig);
    for (let i = 0; i < photoPosts.length; i++) {

        let sum = 0;
        for (let k of kkey) {
            if (k != 'hashtags') {

                if (photoPosts[i][k] == filterConfig[k]) sum++;
            }
            else if (k === 'hashtags') {
                sum += iteration(photoPosts[i][k], filterConfig[k]);
            }

        } if (sum == kkey.length) result.push(photoPosts[i]);
    }

    var dd = document.getElementsByClassName("right-list")[0];

  
    let tmp = "";
    for (let i = 0; i < result.length; i++) {
        tmp += VV.view(result[i]);
    }
    if (result.length == 0) dd.innerHTML = '<h1>По вашему запросу ничего не найдено. Уточните критерии поиска</h1>';
     else dd.innerHTML = tmp;
}

