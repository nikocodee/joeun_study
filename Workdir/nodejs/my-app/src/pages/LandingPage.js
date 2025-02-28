import { Col, Container, Row } from "react-bootstrap";
import Header from "../components/Header";
import ProductCard from "../components/ProductCard";
import Footer from "../components/Footer";

const products = [
  {
    id: 1,
    name: "Product 1",
    description: "This is a great product!",
    price: 29.99,
    image: "https://placehold.co/300x200",
  },
  {
    id: 2,
    name: "Product 2",
    description: "You will love this product.",
    price: 49.99,
    image: "https://placehold.co/300x200",
  },
  {
    id: 3,
    name: "Product 3",
    description: "A must-have item!",
    price: 19.99,
    image: "https://placehold.co/300x200",
  },
];

const LandingPage = () => {
  return (
    <div>
      <Header />
      <div className="bg-light text-center py-5">
        <Container>
          <h1>Welcome to MyShop</h1>
          <p>Your one-stop shop for amazing products.</p>
        </Container>
      </div>
      <Container id="products" className="py-5">
        <h2 className="text-center mb-4">Our Products</h2>
        <Row>
          {products.map((product) => (
            <Col lg={4} md={6} key={product.id}>
              <ProductCard product={product} />
            </Col>
          ))}
        </Row>
      </Container>
      <Footer />
    </div>
  );
};

export default LandingPage;
