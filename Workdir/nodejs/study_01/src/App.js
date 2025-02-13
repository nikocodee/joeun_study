import "./App.css";
import note from "./loopy.jpg";

function App() {
  return (
    <div>
      <h1> Coding ... (❁´◡`❁)</h1>
      <div className="container">
        <img src={note} className="loopy" alt="loopy" />
        <p>
          JAVA
          <br />
          Python
          <br />
          HTML
          <br />
          CSS
          <br />
          JavaScript
          <br />
          React
          <br />
          Oracle
        </p>
      </div>
    </div>
  );
}

export default App;
