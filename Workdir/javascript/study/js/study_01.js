// alert("알림창"); //확인을 누를때까지 대기
// document.write("<h1>테스트</h1>");
// console.log("테스트 콘솔");
// document.write("테스트 html");

// var answer = confirm("표시 문구");
// alert("선택한 값 : " + answer);
// console.log(answer);

// var inputkey = prompt("나이를 입력", "숫자로 입력");
// var inputkey = prompt("나이를 입력");
// alert(inputkey);

// alert(name); //hoisting 호이스팅
// var name = "홍길동";
// var number = 5;

// if (number > 0) {
//   var name = "김철수";
// }
// console.log("name : ", name); //김철수 출력됨

// alert(name); //초기화전에 출력 안됨
// let name = "홍길동";
// let number = 5;

// if (number > 0) {
//   let name = "김철수";
//   //   let name = "홍길동"; // 중복 불가
// }
// console.log("name : ", name); //홍길동 출력됨

function logscope() {
  var local = 2;
  if (local) {
    var local = 8;
    console.log(local);
  }
  console.log(local);
}
logscope();
