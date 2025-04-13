import React from "react";
import FormInput from "../components/UserAuth/FormInput";
import useCreateBook from "../hooks/useCreateBook";

export const CreateBook = () => {
  const [formData, setFormData] = React.useState({
    title: "",
    author: "",
    genre: "",
    description: "",
    yearPublished: "",
    isbn: "",
    price: "",
  });

  const { createBook, loading, error } = useCreateBook();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const { success, error } = await createBook(formData);
      if (success) {
        alert("Book added successfully!");
      } else {
        console.error("Error creating book", error);
        alert("No success creating book");
      }
    } catch (error) {
      console.error("Error creating book", error);
      alert("Error creating book");
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
            {loading ? "Signing up..." : "Sign Up"}
          </button>
          {error && <p style={{ color: "red" }}>{error}</p>}
        </form>
      </div>
    </div>
  );
};
