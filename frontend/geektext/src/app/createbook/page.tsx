"use client";

import FormInput from "../ui/FormInput";
import { useState } from "react";
import { Book } from "../types/book";


export default function CreateBook() {
  const [formData, setFormData] = useState<Book>({
    title: "",
    author: {
      firstName: "",
      lastName: "",
    },
    genre: "",
    description: "",
    yearPublished: 0,
    isbn: 0,
    price: 0,
    copiesSold: 0,
  });

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    if (name.includes(".")) {
      const [parent, child] = name.split(".");
      setFormData((prev) => ({
        ...prev,
        [parent]: {
          ...(prev as any)[parent],
          [child]: value,
        },
      }));
    } else {
      setFormData((prev) => ({ ...prev, [name]: value }));
    }
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      const response = await fetch("http://localhost:8080/api/book/add", {
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
        author: {
          firstName: "",
          lastName: "",
        },
        genre: "",
        description: "",
        yearPublished: 0,
        isbn: 0,
        price: 0,
        copiesSold: 0,
      });
    } catch (err: any) {
      console.error(err.message || "Something went wrong");
      alert("Error creating book");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="bg-green-500 flex flex-col items-center justify-center md:h-screen">
      <div className="w-full p-5 rounded-lg shadow sm:max-w-md bg-white">
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
            placeholder="Journey to the Center of the Earth"
          />
          <FormInput
            htmlFor="authorFirstName"
            header="Author First Name"
            inputType="text"
            inputName="author.firstName"
            value={formData.author.firstName}
            onChange={handleChange}
            placeholder="Jules"
          />
          <FormInput
            htmlFor="authorLastName"
            header="Author Last Name"
            inputType="text"
            inputName="author.lastName"
            value={formData.author.lastName}
            onChange={handleChange}
            placeholder="Verne"
          />
          <FormInput
            htmlFor="genre"
            header="Genre"
            inputType="text"
            inputName="genre"
            value={formData.genre}
            onChange={handleChange}
            placeholder="Science Fiction"
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
            inputType="number"
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
          <FormInput
            htmlFor="copiesSold"
            header="Copies Sold"
            inputType="number"
            inputName="copiesSold"
            value={formData.copiesSold}
            onChange={handleChange}
            placeholder=""
          />
          <button
            type="submit"
            disabled={loading}
            className="w-full mt-5 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center"
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
