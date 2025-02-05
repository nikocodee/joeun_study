// function alt(){
//     const alert = document.getElementById('my_checkbox');
//     // alert("알림창 테스트");
//     return false;
// }

function alt(e){
    const checkbox = document.getElementById('my_checkbox');
    alert(checkbox.checked? 'true' : 'false');
}

function test(){
    alert("Call");
    // return void 아무것도 안함
    return;
}