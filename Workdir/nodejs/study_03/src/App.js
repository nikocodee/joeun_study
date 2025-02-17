/* eslint-disable */
import logo from "./logo.svg";
import "./App.css";
import { useState, useEffect } from "react";
// import WebSocketDemo from "./WebSocketDemo";

function App() {
  const [query, setQuery] = useState("");
  const [result, setResult] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (query === "") {
      setResult([]);
      return;
    }
    setLoading(true);
    const fetchData = () => {
      try {
        const response = fetch(
          `https://jsonplaceholder.typicode.com/users?name_like=${query}`
        );
        const data = response.json();
        setResult(data);
      } catch (e) {
        console.log(e);
      } finally {
        setLoading(false);
      }
    };

    const timetoutId = setTimeout(fetchData, 500);
    return () => clearTimeout(timetoutId);
  }, [query]);

  return (
    <div>
      <input
        type="text"
        value={query}
        onChange={(e) => setQuery(e.target.value)}
      />
      <div>
        {query} : {result}
      </div>
    </div>
  );

  // return (
  //   <div>
  //     <WebSocketDemo />
  //   </div>
  // );
}

export default App;
