import "./App.css";
import { Routes, Route, Link } from "react-router-dom";
import Home from "./pages/Home";
import About from "./pages/About";
import DynamicLink from "./pages/DynamicLink";
import UserDetails from "./pages/UserDetails";

function App() {
  return (
    <div>
      <nav>
        <Link to="/">Home</Link>
        {" | "}
        <Link to="/about">About</Link>
        {" | "}
        <Link to="/user">User List</Link>
      </nav>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/user" element={<DynamicLink />} />
        <Route path="/user/:id/:name" element={<UserDetails />} />
      </Routes>
    </div>
  );
}

export default App;
