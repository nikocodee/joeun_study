import { useState } from "react";
import { Link } from "react-router-dom";

function UserList() {
  const initialUsers = [
    {
      id: 1,
      name: "홍길동",
      age: 20,
      addr1: "부산광역시",
      addr2: "무슨구 무슨동",
    },
    {
      id: 2,
      name: "김갑동",
      age: 30,
      addr1: "서울특별시",
      addr2: "관악구 무슨동",
    },
    {
      id: 3,
      name: "박철수",
      age: 40,
      addr1: "대전광역시",
      addr2: "서구 무슨동",
    },
  ];

  const [users, setUsers] = useState(initialUsers);

  const handleSelectChange = (id, newAddr1) => {
    setUsers((users) =>
      users.map((user) =>
        user.id === id ? { ...user, addr1: newAddr1 } : user
      )
    );
  };

  return (
    <div>
      <table>
        <thead>
          <tr>
            <td>ID</td>
            <td>이름</td>
            <td>나이</td>
            <td>주소</td>
          </tr>
        </thead>
        <tbody>
          {users.map((user) => (
            <tr key={user.id}>
              <td>{user.id}</td>
              <td>
                <Link
                  to={`/user/${user.id}/${user.name}/${user.age}/${user.addr1}/${user.addr2}`}
                >
                  {user.name}
                </Link>
              </td>
              <td>{user.age}</td>
              <select
                value={user.addr1}
                onChange={(e) => handleSelectChange(user.id, e.target.value)}
              >
                <option value="서울특별시">서울특별시</option>
                <option value="부산광역시">부산광역시</option>
                <option value="대전광역시">대전광역시</option>
              </select>
              <td>{user.addr1 + " " + user.addr2}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default UserList;
