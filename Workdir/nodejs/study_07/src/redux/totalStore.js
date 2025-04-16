import { createStore } from "redux";
import totalReducer from "./totalReducer";

const totalStore = createStore(totalReducer);

export default totalStore;
