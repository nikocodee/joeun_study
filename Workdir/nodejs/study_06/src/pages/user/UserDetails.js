import { useParams } from "react-router-dom";

const UserDetails = () => {
  const { name, id } = useParams();
  return (
    <div>
      <p>Details Page</p>
      <p>ID : {id}</p>
      <p>Name : {name}</p>
    </div>
  );
};

export default UserDetails;
