// app/page.tsx or app/home/page.tsx
import BookCard from "@/app/ui/Books/BookCard";
import { Book } from "@/app/types/book";


const endpoint = "http://localhost:8080/api/book/list";

export default async function Home() {
  const res = await fetch(endpoint, {
    cache: "no-store",
  });

  const books: Book[] = await res.json();

  return (
    <>
      <section className="flex gap-5 flex-wrap justify-center m-5">
        {books && books.map((book) => (
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
