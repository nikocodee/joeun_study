const initialState = { total: 0 };

function totalReducer(state = initialState, action) {
  switch (action.type) {
    case "Inc5":
      return { total: state.total + 5 };
    case "Dec5":
      return { total: state.total - 5 };
    default:
      return state;
  }
}

export default totalReducer;
