import { useState, useEffect } from "react";

const endpoint = "http://localhost:8080/api/book/";


const fetchBookDetails = (isbn) => {
  const [book, setBook] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        setIsLoading(true);
        const response = await fetch(endpoint , isbn);

        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }

        const jsonData = await response.json();
        setBook(jsonData);
      } catch (error) {
        setError(error.message);
      } finally {
        setIsLoading(false);
      }
    };
    fetchData();
  }, [endpoint]);

  return { book, isLoading, error };
};

export default fetchBookDetails;
