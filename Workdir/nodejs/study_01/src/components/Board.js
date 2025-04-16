import React, { useState } from "react";
import "../App.css";

function Board() {
  const [count, setCount] = useState(0);
  const [books, setBooks] = useState([]);
  let display =
    count > 10
      ? [`카운트 : ${count} : 10보다 큼`]
      : [`카운트 : ${count} : 10보다 작거나 같음`];
  console.log(`Board() 호출 ${count}`);

  const user = { name: "John", age: 30, job: "Programmer" };
  const { age, name } = user;
  //   const names = user.name;
  //   const ages = user.age;
  console.log(name, age);

  const { a, c, ...rest } = { a: 1, b: 2, c: 3 };
  console.log(rest); //{b: 2}

  return (
    <div className="App">
      <header className="App-header">
        <h1>React 상태 관리 {books}</h1>
        <p>현재 카운트: {count}</p>
        <button onClick={() => setCount(count + 1)}> 카운트 증가 </button>
        <button onClick={() => setBooks(display)}> 리스트 변경 </button>
      </header>
    </div>
  );
}

export default Board;
