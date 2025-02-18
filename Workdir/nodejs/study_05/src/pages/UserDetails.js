import { useParams } from "react-router-dom";

const UserDetails = () => {
  const { id, name } = useParams();

  return (
    <div>
      <h1>User Details</h1>
      <hr />
      <p>User ID: {id}</p>
      <p>User Name: {name}</p>
    </div>
  );
};

export default UserDetails;
