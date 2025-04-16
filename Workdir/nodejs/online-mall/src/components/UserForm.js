import React, { useState } from "react";

function UserForm() {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
  });

  const [errors, setErrors] = useState({});

  function handleChange(event) {
    const { name, value } = event.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
    validate();
  }

  function validate() {
    const newErrors = {};
    if (!formData.name.trim()) {
      newErrors.name = "Name cannot be empty.";
    } else if (formData.name.length < 3) {
      newErrors.name = "Name must be at least 3 characters long.";
    }
    if (!formData.email.includes("@")) {
      newErrors.email = "Email must include '@'.";
    }
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  }

  function handleSubmit(event) {
    event.preventDefault(); // 기본 폼 제출 방지
    if (validate()) {
      console.log("Form Submitted", formData);
      // 서버에 데이터 전송 로직 추가 가능
    }
  }

  return (
    <form onSubmit={handleSubmit} style={{ maxWidth: "400px", margin: "auto" }}>
      <div style={{ marginBottom: "10px" }}>
        <label>
          Name:
          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
            style={{
              display: "block",
              width: "100%",
              padding: "8px",
              margin: "5px 0",
            }}
          />
        </label>
        {errors.name && <p style={{ color: "red" }}>{errors.name}</p>}
      </div>
      <div style={{ marginBottom: "10px" }}>
        <label>
          Email:
          <input
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            style={{
              display: "block",
              width: "100%",
              padding: "8px",
              margin: "5px 0",
            }}
          />
        </label>
        {errors.email && <p style={{ color: "red" }}>{errors.email}</p>}
      </div>
      <button
        type="submit"
        style={{
          padding: "10px 20px",
          backgroundColor: "#333",
          color: "#fff",
          border: "none",
        }}
      >
        Submit
      </button>
    </form>
  );
}

export default UserForm;
