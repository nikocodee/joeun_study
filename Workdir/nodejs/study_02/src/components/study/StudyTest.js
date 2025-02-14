import { useState } from "react";

const StudyTest = () => {
  const [todos, setTodos] = useState([]);
  const [input, setInput] = useState("");
  const [lists, setLists] = useState([]);

  const handleAddTodo = () => {
    if (input) {
      setTodos([
        ...todos,
        { id: Date.now(), text: input, state: "registered" },
      ]);
      setInput("");
    }
  };

  const handleDeleteTodo = (id) => {
    setTodos(todos.filter((todo) => todo.id !== id));
    const deleteList = todos.find((todo) => todo.id === id);
    setLists([...lists, { ...deleteList, state: "deleted" }]);
  };
  return (
    <div>
      <input
        type="text"
        value={input}
        onChange={(e) => setInput(e.target.value)}
        onKeyDown={(e) => {
          if (e.key === "Enter") {
            handleAddTodo();
          }
        }}
      />
      <button onClick={handleAddTodo}>Add</button>
      <ul>
        {todos.map((todo) => (
          <li key={todo.id}>
            {todo.text}
            <button onClick={() => handleDeleteTodo(todo.id)}>Delete</button>
          </li>
        ))}
      </ul>
      <h3>&nbsp; 서버 전송 목록 </h3>
      <ul>
        {todos.map((todo) => (
          <li key={todo.id}>
            {todo.id}&nbsp;/&nbsp;{todo.text}&nbsp;/&nbsp;{todo.state}
          </li>
        ))}
        {lists.map((list) => (
          <li key={list.id}>
            {list.id}&nbsp;/&nbsp;{list.text}&nbsp;/&nbsp;{list.state}
          </li>
        ))}
      </ul>
    </div>
  );
};
// const [todos, setTodos] = useState([]);
// const [input, setInput] = useState("");

// const handleAddTodo = () => {
//   if (input) {
//     setTodos([...todos, { id: Date.now(), text: input }]);
//     setInput("");
//   }
// };

// return (
//   <div>
//     <input
//       type="text"
//       value={input}
//       onChange={(e) => setInput(e.target.value)}
//       onKeyDown={(e) => {
//         if (e.key === "Enter") {
//           handleAddTodo();
//         }
//       }}
//     />
//     <button onClick={handleAddTodo}>Add</button>
//     <ul>
//       {todos.map((todo) => (
//         <li key={todo.id}>{todo.text}</li>
//       ))}
//     </ul>
//   </div>
// );

// const [names, setNames] = useState([]);
// let result = ["A", "F", "B"];
// // result = ["11A", "22F", "33B"];
// result = ["ji11", "jj33", "s33가"];
// return (
//   <div>
//     <ul>
//       {names.map((name, index) => (
//         <li key={index}>{name}</li>
//       ))}
//     </ul>
//     <span
//       onClick={() => {
//         setNames([]);
//       }}
//     >
//       초기화
//     </span>
//     ||||
//     <span
//       onClick={() => {
//         setNames(result);
//       }}
//     >
//       조회
//     </span>
//   </div>
// );
// };

// const StudyTest = () => {
//   const [text, setText] = useState("");
//   //   let display = "123";

//   const handleChange = (event) => {
//     setText(event.target.value);
//   };

//   return (
//     <div>
//       {/* <input type="text" value={display} onChange={handleChange} /> */}
//       <input type="text" value={text} onChange={handleChange} />
//       <p>Typed: {text}</p>
//     </div>
//   );

// useState는 static 정적변수
//   const [count, setCount] = useState(0);
//   const label = 0;
//   let test = "test";
//   test = 7;

//   const increment = () => setCount(count + 1);
//   const decrement = () => setCount(count - 1);

//   return (
//     <div>
//       <h1>
//         Count: {count} : test : {test} / {label}
//       </h1>
//       <button onClick={increment}>Increase</button>
//       <button onClick={decrement}>Decrease</button>
//     </div>
//   );
// };

export default StudyTest;
