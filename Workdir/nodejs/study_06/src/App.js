import logo from "./logo.svg";
import "./App.css";
import { Routes, Route } from "react-router-dom";
import Home from "./pages/main/Home";
import About from "./pages/main/About";
import { Link } from "react-router-dom";
import UserList from "./pages/user/UserList";
import UserDetails from "./pages/user/UserDetails";

function App() {
  return (
    <div>
      <nav>
        <Link to="/">Home</Link>
        {" | "}
        <Link to="/about">About</Link>
        {" | "}
        <Link to="/users">User List</Link>
      </nav>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/users" element={<UserList />} />
        <Route
          path="/user/:id/:name/:age/:addr1/:addr2"
          element={<UserDetails />}
        />
      </Routes>
    </div>
  );
}

export default App;
