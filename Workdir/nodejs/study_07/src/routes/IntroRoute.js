import { Route, Routes } from "react-router-dom";
import About from "../pages/About";

const IntroRoute = () => {
  return (
    <Routes>
      <Route path="/intro" element={<About />} />
    </Routes>
  );
};

export default IntroRoute;
