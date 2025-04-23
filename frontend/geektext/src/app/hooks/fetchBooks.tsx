import { useState, useEffect } from "react";

type Book = {
  id: number;
  title: string;
  author: string;
  description: string;
  isbn: number;
};

const endpoint = "http://localhost:8080/api/books/all";

const useBooks = () => {
  const [books, setBooks] = useState<Book[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        setIsLoading(true);
        const response = await fetch(endpoint);

        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }

        const jsonData: Book[] = await response.json();
        setBooks(jsonData);
      } catch (err: any) {
        setError(err.message);
      } finally {
        setIsLoading(false);
      }
    };
    fetchData();
  }, []);

  return { books, isLoading, error };
};

export default useBooks;
