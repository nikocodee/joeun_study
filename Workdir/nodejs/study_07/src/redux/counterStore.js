import counterSlice from "./counterSlice";
import { configureStore } from "@reduxjs/toolkit";

const counterStore = configureStore({
  reducer: { counters: counterSlice },
});

export default counterStore;
