import { Suspense, lazy } from "react";
import { Navigate } from "react-router-dom";

const todoRouter = () => {
  const Loading = <div>Loading....</div>;
  const TodoList = lazy(() => import("../pages/todo/ListPage"));
  const TodoRead = lazy(() => import("../pages/todo/ReadPage"));

  return [
    {
      path: "list",
      element: (
        <Suspense fallback={Loading}>
          <TodoList />
        </Suspense>
      ),
    },
    {
      path: "",
      element: <Navigate replace to="/todo/list" />,
    },
    {
      path: "read/:tno",
      element: (
        <Suspense fallback={Loading}>
          <TodoRead />
        </Suspense>
      ),
    },
  ];
};

export default todoRouter;
