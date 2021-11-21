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
    debugger;
    e.preventDefault();
    let data =$("#profileUpdate").serialize();
    let promis = new Promise((resolve, reject) => {
        $.ajax({
            url:"/api/user/{userId}",
            method:"post",
            data:data,
            contentType:"application/x-www-form-urlencoded; charset=utf-8",
            dataType:"json"
        }).done((res)=>{
            resolve(res);
        }).fail((error)=>{
            reject(new Error(error));
        });
    })

    promis
        .then((value)=>{
            console.log(value);
        })
        .catch((error)=>{
            console.log(error);
        })
}
