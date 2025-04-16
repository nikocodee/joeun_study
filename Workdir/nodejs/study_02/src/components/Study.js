import "./Study.css";

// 일반 function
// function Study({ title, name, period }) {
// function Study(props) {
// function Study({ title, ...props }) {
//   const label = 10;
//   let result = calc(label);
//   return (
//     <div>
//       <tabke>
//         <tr>
//           <td>label : {label}</td>
//         </tr>
//         <tr>
//           <td> result : {result}</td>
//         </tr>
//         <tr>
//           <td>
//             {/* name : {name} | title : {title} | period : {period} */}
//             {/* name : {props.name} | title : {props.title} | period */}
//             name : {props.name} | title : {title} | period : {props.period}
//           </td>
//         </tr>
//       </tabke>
//     </div>
//   );
// }

// 익명 함수
const Study = function ({ title, ...props }) {
  const label = 10;
  let result = calc(label);
  return (
    <div>
      <tabke>
        <tr>
          <td>label : {label}</td>
        </tr>
        <tr>
          <td> 결과 : {result}</td>
        </tr>
        <tr>
          <td>
            {/* name : {name} | title : {title} | period : {period} */}
            {/* name : {props.name} | title : {props.title} | period */}
            name : {props.name} | title : {title} | period : {props.period}
          </td>
        </tr>
      </tabke>
    </div>
  );
};

// arrow function
// const Study = ({ title, ...props }) => {
//   const label = 10;
//   let result = calc(label);
//   return (
//     <div>
//       <tabke>
//         <tr>
//           <td>label : {label}</td>
//         </tr>
//         <tr>
//           <td> result : {result}</td>
//         </tr>
//         <tr>
//           <td>
//             {/* name : {name} | title : {title} | period : {period} */}
//             {/* name : {props.name} | title : {props.title} | period */}
//             name : {props.name} | title : {title} | period : {props.period}
//           </td>
//         </tr>
//       </tabke>
//     </div>
//   );
// };

function calc(num) {
  return num * 2;
}

export default Study;
