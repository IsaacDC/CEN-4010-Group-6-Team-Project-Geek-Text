import React from "react";
import Link from "next/link";

export default function BookCard({
  title,
  author,
  description,
  isbn,
  coverImage,
}) {
  return (
    <div className="flex flex-col max-w-sm rounded overflow-hidden shadow-lg">
        <img
          className="w-full mb-4"
          src={coverImage || "https://placehold.co/400x500"}
          alt={`${title}`}
        />
        <div className="px-6 py-4">
          <div className="font-bold text-xl mb-2">
            <Link to={`/book/${isbn}`} className="hover:text-blue-500">
              {title}
            </Link>
          </div>
          <p className="text-gray-700 text-base">{description}</p>
        </div>
        <div className="mt-auto px-6 py-3">
          <span className="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">
            {author}
          </span>
      </div>
    </div>
  );
}
