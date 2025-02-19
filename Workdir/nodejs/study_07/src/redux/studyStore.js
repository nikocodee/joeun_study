import { configureStore } from "@reduxjs/toolkit";
import studyReducer from "./studySlice";

const studyStore = configureStore({
  reducer: { study: studyReducer },
});

export default studyStore;
