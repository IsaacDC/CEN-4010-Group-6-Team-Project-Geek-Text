import { useState } from "react";

const useUser = () => {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const authenticate = async (endpoint, payload) => {
    setLoading(true);
    setError(null);

    try {
      const response = await fetch(endpoint, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(payload),
      });

      const text = await response.text();
      const data = text ? JSON.parse(text) : {};

      switch (response.status) {
        case 401:
          throw new Error("Wrong username or password");
        case 409:
          throw new Error("Username taken");
        case 500:
          throw new Error("Internal server error");
        default:
          if (!response.ok) {
            return {
              success: false,
              error: "Something went wrong. Try again.",
            };
          }
          return { success: true, data };
      }
    } catch (err) {
      setError(err.message);
      return {
        success: false,
        error: err,
      };
    } finally {
      setLoading(false);
    }
  };

  return { authenticate, loading, error };
};

export default useUser;
