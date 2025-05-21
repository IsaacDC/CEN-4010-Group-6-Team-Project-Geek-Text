import React from "react";
import { useParams } from "react-router-dom";
import fetchBookDetails from "../../hooks/fetchBookDetails";
import Header from "../Header/Header";

export default function BookDetails() {
  const { isbn } = useParams();
  const { book, loading, error } = fetchBookDetails(isbn);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;
  if (!book) return <div>No book found with this ISBN.</div>;

  return (
    <>
      <Header />
      <div className="flex justify-center px-5">
        <div className="max-w-sm rounded overflow-hidden shadow-lg">
          <img
            className="w-full"
            src={book.imageUrl || "https://placehold.co/400x500"}
            alt={`${book.title}`}
          />
        </div>
        <div className="flex flex-col p-4">
          <div className="border-b-2 pb-2">
            <div className="font-bold text-xl mb-2">
              <div>{book.title}</div>
            </div>
            <p className="text-sm font-semibold text-gray-700 mb-2">
              By {book.author?.firstName || "Unknown Author"}{" "}
              {book.author?.lastName || "Unknown Author"}
            </p>
            <p className="text-gray-700 text-base">{book.description}</p>
          </div>
          <div className="border-b-2 pb-2">
            <p>Genre: {book.genre}</p>
            <p>Year Published: {book.yearPublished}</p>
            <p>Copies Sold: {book.copiesSold}</p>
            <p className="text-xl font-semibold">${book.price}</p>
          </div>
        </div>
      </div>
    </>
  );
}
