import logo from "./logo.svg";
import "./App.css";
import { useDispatch, useSelector } from "react-redux";
import Home from "./pages/Home";
import About from "./pages/About";
import { Link, Route, Routes } from "react-router-dom";

function App() {
  return (
    <div>
      <nav>
        <Link to={"/"}>Home</Link>
        {" | "}
        <Link to={"/about"}>About</Link>
      </nav>
      <hr />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
      </Routes>
    </div>
  );

  // const totalNo = useSelector((state) => state.total);
  // const dispatch = useDispatch();
  // const INCREMENT_ACTION = { type: "Inc5" };
  // const DECREMENT_ACTION = { type: "Dec5" };
  // return (
  //   <div>
  //     <p>Count : {totalNo}</p>
  //     <button onClick={() => dispatch(INCREMENT_ACTION)}>Inc</button>
  //     {" | "}
  //     <button onClick={() => dispatch(DECREMENT_ACTION)}>Dec</button>
  //   </div>
  // );
}

export default App;
