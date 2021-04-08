class View {
    //(date.toLocaleDateString('en-US'));
    view(post) {
        let dt=new Date(post.createdAt);
        let tmp = '<div class="item">' +
            ' <div class="item-top">' +
            ' <img src="right_images/People_icon.png">' +
            '<span class="fio">' + post.author + '</span>' +
            '<span class="date">' + dt.getDay()+'/'+(dt.getMonth()+1)+'/'+dt.getFullYear() + '</span>' +
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
        for (let k of post.hashtags) {
            tmp1 += k;
        }


        tmp += '</div>' +
            '<div class="hash"  style="grid-area: hash">' + tmp1 + '</div>' +
            '</div>' +
            '<div class="item-bottom">' +
            '<img src="right_images/Heart-icon.png" class="item_like" onclick="Like(this)">'+
            '<img src="right_images/message_icon.png">' +
            '<a href="send_email.html"><img src="right_images/refresh-icon.png"></a>';

            let el = document.getElementsByClassName("name_surname")[0];
           
        if (el.innerHTML ===post.author) {

            tmp += '<img src="right_images/delete.png" id=' + post.id + ' class="item_del" onclick="del(this)">' +
                '<a href="post_edit.html?guid=' + post.id + '"><img src="right_images/ed.png"></a>';
        }
        tmp +=
            '</div>' +
            '</div>';
        return tmp;
    }


    showTenEl(first,last) {
         let tmp='';
        for(let i=first;i<last;i++) {
            tmp += this.view(photoPosts[i]);
        }
        document.getElementsByClassName("right-list")[0].innerHTML=tmp;
    }

    showNew10El(){
        let el = document.getElementsByClassName("right-list")[0];
        let tmp="";
        let size=el.childNodes.length;
        for(let i=size;i<Math.min(size+10, photoPosts.length);i++) {
                tmp += this.view(photoPosts[i]);
            }
        if(tmp.length>0) el.innerHTML+=tmp;
    }

}


