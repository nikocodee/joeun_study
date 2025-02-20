import { Route, Routes } from "react-router-dom";
import About from "../pages/About";

function AboutRoute() {
  return (
    <Routes>
      <Route path="/about" element={<About />} />
    </Routes>
  );
}

export default AboutRoute;
