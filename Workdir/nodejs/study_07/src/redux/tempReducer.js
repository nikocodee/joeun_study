const initialState = { count: 0, total: 0 };

function tempReducer(state = initialState, action) {
  switch (action.type) {
    case "INK10":
      return { count: state.count + 10, total: state.total + state.count };
    case "DEC5":
      return { count: state.count + 5, total: state.total - state.count };
    default:
      break;
  }
}

export default tempReducer;
