import { Link } from "react-router-dom";

function DynamicLink() {
  const users = [
    { id: 1, name: "Alice" },
    { id: 2, name: "Bob" },
    { id: 3, name: "Charlie" },
  ];

  return (
    <div>
      <h1>User List</h1>
      <ul>
        {users.map((user) => (
          <li key={user.id}>
            <Link to={`/user/${user.id}/${user.name}`}>{user.name}</Link>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default DynamicLink;
