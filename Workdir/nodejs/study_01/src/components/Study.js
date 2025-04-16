function Study(props) {
  let param = props.display;
  return (
    <div>
      <p>
        {param} / {props.title}
      </p>
    </div>
  );
}

// function Study({display, title}) {
//     let param = display;
//     return (
//       <div>
//         <p>
//           {param} / {title}
//         </p>
//       </div>
//     );
//   }

export default Study;
