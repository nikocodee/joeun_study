import logo from "./logo.svg";
import "./App.css";
import { useState, useEffect } from "react";

function App() {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        console.log("fetchDate() Call !!!");
        // await fetch
        // 비동기적으로 데이터를 가져오기
        const response = await fetch(
          "https://jsonplaceholder.typicode.com/posts"
        );
        // await response.json
        // fetch 함수를 사용하여 가져온 네트워크 응답을 JSON 형식으로 변환하는 데 사용
        const data = await response.json();
        // alert("결과 : " + JSON.stringify(response));
        setPosts(data.slice(0, 10)); // 첫 10개의 포스트만 가져오기
        setLoading(false);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, []); // 빈 배열: 컴포넌트가 마운트될 때 1회 실행

  return (
    <div>
      {loading ? (
        <p>Loading...</p>
      ) : (
        <ul>
          {posts.map((post) => (
            <li key={post.id}>{post.title}</li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default App;
