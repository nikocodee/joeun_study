import logo from "./logo.svg";
import "./App.css";
import SearchBar from "./components/SearchBar";
import { useState } from "react";

function App() {
  const { searchParams, setSearchParams } = useState({});
  return (
    <div>
      <SearchBar onSearch={{ setSearchParams }} />
    </div>
  );
}

export default App;
