import { faSearch } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";

export default function SearchBar() {
  return (
    <div className=" flex justify-between py-3 px-6 space-x-6">
      <form action="" className=" max-w-md">
        <div className="relative flex items-center text-gray-400 focus-within:text-gray-600">
          <FontAwesomeIcon
            icon={faSearch}
            className="w-5 h-5 absolute ml-3 pointer-events-none"
          />
          <input
            type="text"
            name="search"
            placeholder="Search Book"
            autoComplete="off"
            aria-label="search"
            className="w-full pr-3 pl-10 bg-gray-50 font-semibold placeholder-gray-500 text-black rounded-2xl border-none ring-2 ring-gray-300 focus:ring-gray-500 focus:ring-2"
          />
        </div>
      </form>
    </div>
  );
}
