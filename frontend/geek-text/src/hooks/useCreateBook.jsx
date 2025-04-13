import React, { useState } from "react";

const endpoint = "http://localhost:8080/api/books/add"

const useCreateBook = () => {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const createBook = async (endpoint, payload) => {
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
        throw new Error(`HTTP error! status: ${response.status}`);
      }
    } catch (e) {
      setError(e.message);
      return { success: false, error: e };
    } finally {
      setLoading(false);
    }
  };

  return { createBook, loading, error };
};

export default useCreateBook;
