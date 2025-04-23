// app/page.tsx or app/home/page.tsx
import Header from "@/app/ui/Header/Header";
import BookCard from "@/app/ui/Books/BookCard";

type Book = {
  id: number;
  title: string;
  author: {
    firstName: string;
    lastName: string;
  };
  description: string;
  isbn: number;
  coverImage: string;
};

const endpoint = "http://localhost:8080/api/books/all";

export default async function Home() {
  const res = await fetch(endpoint, {
    cache: "no-store",
  });
  const books: Book[] = await res.json();

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
