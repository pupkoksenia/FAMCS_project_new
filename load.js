let PP=new PostList(ArrOfHashtags, ArrOfNames);

function Load(){
    //localStorage.clear();
    
if (localStorage.length == 0) {
    for (let i = 0; i < photoPostsSource.length; i++) {
        let key =i;
        var serialItem = JSON.stringify(photoPostsSource[i]);
        localStorage.setItem(key, serialItem);
       
        
    }
}

for (let i = 0; i < localStorage.length; i++) {
    let key = localStorage.key(i);
    let obj = JSON.parse(localStorage.getItem(key));
    photoPosts.push(obj);
}

}
