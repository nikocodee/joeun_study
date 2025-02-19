import { useParams } from "react-router-dom";

const UserDetails = () => {
  const { name, id, age, addr1, addr2 } = useParams();
  return (
    <div>
      <p>Details Page</p>
      <p>ID : {id}</p>
      <p>Name : {name}</p>
      <p>Age : {age} </p>
      <p>Address : {addr1 + " " + addr2} </p>
    </div>
  );
};

export default UserDetails;
