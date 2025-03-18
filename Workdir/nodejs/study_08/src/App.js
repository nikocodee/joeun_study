import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import BoardList from "./components/BoardList";
import BoardDetail from "./pages/BoardDetail";
import BoardForm from "./components/BoardForm";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<BoardList />} />
        <Route path="/users/:id" element={<BoardDetail />} />
        <Route path="/users/create" element={<BoardForm />} />
      </Routes>
    </Router>
  );
}

export default App;
