import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import useUser from "../../hooks/useUser";
import FormInput from "./FormInput";

export default function LogInCard({ toggleAuthMode }) {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });
  const { authenticate, loading, error } = useUser();
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const endpoint = "http://localhost:8080/api/user/verify";
    const { success, data, status } = await authenticate(endpoint, formData);
    if (success) {
      navigate("/home");
    } else {
      if (status === 401) {
        setErrorMessage("Invalid username or password.");
      } else if (status === 500) {
        setErrorMessage("Server error. Please try again later.");
      } else {
        setErrorMessage(`Unexpected error (${status}). Please try again.`);
      }
    }
  };

  return (
    <div className="w-full rounded-lg shadow md:mt-0 sm:max-w-md xl:p-0 bg-white">
      <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
        <h1
          className="text-xl font-bold leading-tight tracking-tight text-green-900 md:text-2xl text-emerald-300"
          style={{ color: "var(--primary-color" }}
        >
          Log In
        </h1>
        <form className="space-y-4 md:space-y-6" onSubmit={handleSubmit}>
          <FormInput
            htmlFor="username"
            header="Username"
            inputType="text"
            inputName="username"
            value={formData.username}
            onChange={handleChange}
            placeholder="johndoe123"
          />
          <FormInput
            htmlFor="password"
            header="Password"
            inputType="password"
            inputName="password"
            value={formData.password}
            onChange={handleChange}
            placeholder="Password"
          />
          <button
            type="submit"
            disabled={loading}
            className="w-full hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center"
            style={{
              color: "var(--secondary-color)",
              backgroundColor: "var(--dark-green)",
            }}
          >
            {loading ? "Logging In..." : "Log In"}
          </button>
          {error && <p style={{ color: "red" }}>Try Again</p>}
        </form>
        <div>
          <p className="text-sm">
            Don't have an account?{" "}
            <button
              style={{ color: "var(--secondary-color)" }}
              className="font-medium hover:underline"
              onClick={toggleAuthMode}
            >
              Sign Up
            </button>
          </p>
        </div>
      </div>
    </div>
  );
}
