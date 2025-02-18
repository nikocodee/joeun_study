import { Link } from "react-router-dom";

function UserList() {
  const users = [
    { id: 1, name: "홍길동", age: 20, addr1: "부산", addr2: "무슨구 무슨동" },
    { id: 2, name: "김갑동", age: 30, addr1: "서울", addr2: "관악구 무슨동" },
    { id: 3, name: "박철수", age: 40, addr1: "대전", addr2: "서구 무슨동" },
  ];

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
            <tr>
              <td>{user.id}</td>
              <td>
                <Link to={`/user/${user.id}/${user.name}`}>{user.name}</Link>
              </td>
              <td>{user.age}</td>
              <select defaultValue={user.addr1}>
                <option value="부산">부산광역시</option>
                <option value="서울">서울특별시</option>
                <option value="대전">대전광역시</option>
              </select>
              {/* <select>
                <option
                  value="서울"
                  selected={user.addr1 === "서울" ? "selected" : ""}
                >
                  서울특별시
                </option>
                <option
                  value="대전"
                  selected={user.addr1 === "대전" ? "selected" : ""}
                >
                  대전광역시
                </option>
                <option
                  value="부산"
                  selected={user.addr1 === "부산" ? "selected" : ""}
                >
                  부산광역시
                </option>
              </select> */}
              <td>{user.addr1 + " " + user.addr2}</td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* <table>
        <thead>
          <tr>
            <td>ID</td>
            <td>이름</td>
            <td>나이</td>
            <td>주소</td>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>
              <Link to="/user/1/홍길동">홍길동</Link>
            </td>
            <td>20</td>
            <td>서울 관악구 무슨동</td>
          </tr>
          <tr>
            <td>2</td>
            <td>
              <Link to="/user/2/김갑동">김갑동</Link>
            </td>
            <td>34</td>
            <td>경기도 부천시</td>
          </tr>
          <tr>
            <td>3</td>
            <td>
              <Link to="/user/3/박철수">박철수</Link>
            </td>
            <td>41</td>
            <td>대전시 서구</td>
          </tr>
        </tbody>
      </table> */}
    </div>
  );
}

export default UserList;
