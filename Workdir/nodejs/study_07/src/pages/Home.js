import { useDispatch, useSelector } from "react-redux";
// import { decrement,increment } from "../redux/counterSlice";
import { addTotal, subTotal } from "../redux/studySlice";

function Home() {
  const totalNo = useSelector((state) => state.study.total);
  const countNo = useSelector((state) => state.study.count);
  const dispatch = useDispatch();
  // const INCREMENT_ACTION = { type: "INCREMENT" };
  // const DECREMENT_ACTION = { type: "DECREMENT" };
  return (
    <div>
      <p>Home Page</p>

      <p>Total : {totalNo}</p>
      <p>Count : {countNo}</p>
      <button onClick={() => dispatch(addTotal())}>Inc</button>
      {" | "}
      <button onClick={() => dispatch(subTotal())}>Dec</button>
    </div>
  );
}

export default Home;
