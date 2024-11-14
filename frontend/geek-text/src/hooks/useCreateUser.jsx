import { useState } from "react";

const useCreateUser = () => {
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

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "Something went wrong!");
      }

      const data = await response.json();
      return { success: true, data };
    } catch (err) {
      setError(err.message || "Failed to submit.");
      return { success: false, error: err.message };
    } finally {
      setLoading(false);
    }
  };

  return { authenticate, loading, error };
};

export default useCreateUser;
