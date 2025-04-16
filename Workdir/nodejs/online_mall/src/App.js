import logo from "./logo.svg";
import "./App.css";
import { Route, Router, Routes } from "react-router-dom";
import Header from "./components/Header";
import Footer from "./components/Footer";
import CategoryPage from "./pages/CategoryPage";
import ProductDetailPage from "./pages/ProductDetailPage";
import CartPage from "./pages/CartPage";
import AdminProductPage from "./pages/AdminProductPage";
import { useCart } from "./contexts/CartContext";

function App() {
  const { addToCart } = useCart();

  return (
    <Router>
      <Header />
      <Routes>
        <Route path="/category/:categoryId" element={<CategoryPage />} />
        <Route
          path="/product/:productId"
          element={<ProductDetailPage onAddToCart={addToCart} />}
        />
        <Route path="/cart" element={<CartPage />} />
        <Route path="/admin/products" element={<AdminProductPage />} />
      </Routes>
      <Footer />
    </Router>
  );
}

export default App;
