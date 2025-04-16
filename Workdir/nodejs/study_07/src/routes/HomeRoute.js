import { Route, Routes } from "react-router-dom";
import Home from "../pages/Home";

function HomeRoute() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/main" element={<Home />} />
    </Routes>
  );
}

export default HomeRoute;
