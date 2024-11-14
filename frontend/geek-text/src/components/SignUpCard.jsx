import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import useCreateUser from "../hooks/useCreateUser";

export default function SignUpCard({ toggleAuthMode }) {
  const [formData, setFormData] = useState({
    fullname: "",
    username: "",
    email: "",
    address: "",
    password: "",
  });

  const { authenticate, loading, error } = useCreateUser();
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
    const endpoint = "http://localhost:8080/api/user/add";
    try {
      const { success, data } = await authenticate(endpoint, formData);
      if (success) {
        navigate("/");
      } else {
        console.error("Error add user", data.error);
      }
    } catch (err) {
      console.error("Error adding user", err);
    }
  };

  return (
    <section style={{ backgroundColor: "var(--primary-color" }}>
      <div className="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
        <a
          className="flex items-center mb-6 text-2xl font-semibold"
          style={{ color: "var(--dark-green)" }}
        >
          GeekText
        </a>
        <div className="w-full rounded-lg shadow md:mt-0 sm:max-w-md xl:p-0 bg-white">
          <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
            <h1
              className="text-xl font-bold leading-tight tracking-tight text-green-900 md:text-2xl text-emerald-300"
              style={{ color: "var(--primary-color" }}
            >
              Sign Up
            </h1>
            <form className="space-y-4 md:space-y-6" onSubmit={handleSubmit}>
              <div>
                <label
                  htmlFor="fullname"
                  className="block mb-2 text-sm font-medium"
                  style={{ color: "var(--primary-color" }}
                >
                  Your Name
                </label>
                <input
                  type="text"
                  name="fullname"
                  id="fullname"
                  value={formData.name}
                  onChange={handleChange}
                  className="bg-green-100 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5"
                  placeholder="John Doe"
                  required
                />
              </div>
              <div>
                <label
                  htmlFor="name"
                  className="block mb-2 text-sm font-medium"
                  style={{ color: "var(--primary-color" }}
                >
                  Username
                </label>
                <input
                  type="text"
                  name="username"
                  id="username"
                  value={formData.username}
                  onChange={handleChange}
                  className="bg-green-100 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5"
                  placeholder="Username1234"
                  required
                />
              </div>
              <div>
                <label
                  htmlFor="address"
                  className="block mb-2 text-sm font-medium"
                  style={{ color: "var(--primary-color" }}
                >
                  Your Address
                </label>
                <input
                  type="text"
                  name="address"
                  id="address"
                  value={formData.address}
                  onChange={handleChange}
                  className="bg-green-100 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5"
                  placeholder="12345 NW 10th Road"
                  required
                />
              </div>
              <div>
                <label
                  htmlFor="password"
                  className="block mb-2 text-sm font-medium"
                  style={{ color: "var(--primary-color" }}
                >
                  Password
                </label>
                <input
                  type="password"
                  name="password"
                  id="password"
                  value={formData.password}
                  onChange={handleChange}
                  placeholder="••••••••"
                  className="bg-green-100 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5"
                  required=""
                />
              </div>

              <button
                type="submit"
                disabled={loading}
                className="w-full hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center"
                style={{
                  color: "var(--secondary-color)",
                  backgroundColor: "var(--dark-green)",
                }}
              >
                {loading ? "Signing up..." : "Sign Up"}
              </button>
              {error && <p style={{ color: "red" }}>Try Again</p>}
            </form>
            <div>
              <p className="text-sm">
                Already have an account?{" "}
                <a
                  href="#"
                  style={{ color: "var(--secondary-color)" }}
                  className="font-medium hover:underline"
                  onClick={toggleAuthMode}
                >
                  Log In
                </a>
              </p>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
