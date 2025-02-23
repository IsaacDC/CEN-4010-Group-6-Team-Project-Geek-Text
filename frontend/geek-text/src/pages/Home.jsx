import React from "react";
import Header from "../components/Header/Header";
import BookCard from "../components/BookCard";
import useBooks from "../hooks/useBooks";


export default function Home() {
  
  const { books, loading, error } = useBooks();

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;

  return (
    <>
      <Header />
      <section className="flex gap-5 flex-wrap justify-center">
        {books.map((book) => (
          <BookCard
            key={book.isbn}
            title={book.title}
            author={`${book.author.firstName} ${book.author.lastName}`}
            description={book.description}
            isbn={book.isbn}
            coverImage={book.coverImage}
          />
        ))}
      </section>
    </>
  );
}
