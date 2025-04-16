import { createSlice } from "@reduxjs/toolkit";

const studySlice = createSlice({
  name: "study",
  initialState: { total: 100, count: 0 },
  reducers: {
    addTotal: (state, action) => {
      state.total += 2;
      state.count += 1;
    },
    subTotal: (state, action) => {
      state.total -= 2;
      state.count -= 1;
    },
  },
});

export const { addTotal, subTotal } = studySlice.actions;
export default studySlice.reducer;
