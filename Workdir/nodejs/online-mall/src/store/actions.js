export const addToCart = (product) => ({
  type: "ADD_TO_CART", //함수 이름
  payload: product, //전달하고자 하는 값
});
