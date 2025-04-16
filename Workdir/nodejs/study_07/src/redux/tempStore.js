import { createStore } from "redux";
import tempReducer from "./tempReducer";

const tempStore = createStore(tempReducer);

export default tempStore;
