"use client";

import FormInput from "../ui/FormInput";
import { useState } from "react";

type FormData = {
  title: string;
  author: string;
  genre: string;
  description: string;
  yearPublished: Number;
  isbn: Number;
  price: Number;
};

export default function CreateBook() {
  const [formData, setFormData] = useState<FormData>({
    title: "",
    author: "",
    genre: "",
    description: "",
    yearPublished: 0,
    isbn: 0,
    price: 0,
  });

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      const response = await fetch("http://localhost:8080/api/books/create", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });
      if (!response.ok) {
        throw new Error(`Failed to create book: ${response.status}`);
      }
      alert("Book created successfully!");
      setFormData({
        title: "",
        author: "",
        genre: "",
        description: "",
        yearPublished: 0,
        isbn: 0,
        price: 0,
      });
    } catch (err: any) {
      console.error(err.message || "Something went wrong");
      alert("Error creating book");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
      <div className="w-full rounded-lg shadow md:mt-0 sm:max-w-md xl:p-0 bg-white">
        <a
          className="flex items-center mb-6 text-2xl font-semibold"
          style={{ color: "var(--dark-green)" }}
        >
          Add Book
        </a>
        <form onSubmit={handleSubmit}>
          <FormInput
            htmlFor="title"
            header="Title"
            inputType="text"
            inputName="title"
            value={formData.title}
            onChange={handleChange}
            placeholder=""
          />
          <FormInput
            htmlFor="author"
            header="Author"
            inputType="text"
            inputName="author"
            value={formData.author}
            onChange={handleChange}
            placeholder=""
          />
          <FormInput
            htmlFor="genre"
            header="Genre"
            inputType="text"
            inputName="genre"
            value={formData.genre}
            onChange={handleChange}
            placeholder=""
          />
          <FormInput
            htmlFor="description"
            header="Description"
            inputType="text"
            inputName="description"
            value={formData.description}
            onChange={handleChange}
            placeholder=""
          />
          <FormInput
            htmlFor="yearPublished"
            header="Year Published"
            inputType="number"
            inputName="yearPublished"
            value={formData.yearPublished}
            onChange={handleChange}
            placeholder=""
          />
          <FormInput
            htmlFor="isbn"
            header="ISBN"
            inputType="text"
            inputName="isbn"
            value={formData.isbn}
            onChange={handleChange}
            placeholder=""
          />
          <FormInput
            htmlFor="price"
            header="Price"
            inputType="number"
            inputName="price"
            value={formData.price}
            onChange={handleChange}
            placeholder=""
          />
          <button
            type="submit"
            disabled={loading}
            className="w-full hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center"
            style={{
              color: "var(--secondary-color)",
              backgroundColor: "var(--dark-green)",
            }}
          >
            Enter
          </button>
          {error && <p style={{ color: "red" }}>{error}</p>}
        </form>
      </div>
    </div>
  );
}
