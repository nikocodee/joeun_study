import logo from "./logo.svg";
import "./App.css";
import { useState, useEffect } from "react";

function App() {
  const [query, setQuery] = useState("");
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(false);

  const fetchDataClick = async () => {
    // alert("fetchData() Call !!!");
    setLoading(true);
    try {
      const response = await fetch(
        `https://jsonplaceholder.typicode.com/users?name_like=${query}`
      );
      const data = await response.json();
      setUsers(data);
    } catch (e) {
      console.log(e);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (query === "") {
      setUsers([]);
      return;
    }

    const fetchData = async () => {
      // alert("fetchData() Call !!!");
      setLoading(true);
      try {
        const response = await fetch(
          `https://jsonplaceholder.typicode.com/users?name_like=${query}`
        );
        const data = await response.json(); // Object -> JSON
        const dataString = JSON.stringify(data); // JSON -> String
        const dataJson = JSON.parse(dataString); // String -> JSON
        // alert(data);
        alert(dataString);
        // alert(data[0].name);
        alert("0인덱스 name : " + dataJson[0]["name"]);
        setUsers(data);
      } catch (e) {
        console.log("에러 : ", e);
      } finally {
        setLoading(false);
      }
    };

    const timeoutId = setTimeout(fetchData, 500);
    return () => clearTimeout(timeoutId);
  }, [query]);

  return (
    <div>
      <input
        type="text"
        value={query}
        onChange={(e) => setQuery(e.target.value)}
      />
      <button onClick={() => fetchDataClick()}>조회</button>
      <hr />
      <p>
        {loading ? (
          <p>loading...</p>
        ) : (
          <p>
            <ul>
              {users.map((user) => (
                <li key={user.id}>{user.name}</li>
              ))}
            </ul>
          </p>
        )}
      </p>
    </div>
  );
}

export default App;
