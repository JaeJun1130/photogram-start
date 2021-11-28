// (1) 회원정보 수정
//https://lee-mandu.tistory.com/437 promise 무한콜백 탈
function update(userId,e) {
    e.preventDefault();출
    let data =$("#profileUpdate").serialize();
    $.ajax({
    }).done((res)=>{
    }).fail((error)=>{
    });
}

function promisAjax(userId,e) {
    e.preventDefault();
    let data =$("#profileUpdate").serialize();
    let url ="/api/user/"+userId;
    let promis = new Promise((resolve, reject) => {
        $.ajax({
            url:url,
            method:"post",
            data:data,
            contentType:"application/x-www-form-urlencoded; charset=utf-8",
            dataType:"json"
        }).done((res)=>{ //상태코드가 200
            resolve(res);
        }).fail((error)=>{// 상태코드가 200이 아닐때 fail
            reject(error);
        });
    });

    promis
        .then((value)=>{
            console.log(value);
            location.href= "/user/"+userId;
        })
        .catch((error)=>{
            console.log(error);
            alert(JSON.stringify(error.responseJSON.data))
        })
}
