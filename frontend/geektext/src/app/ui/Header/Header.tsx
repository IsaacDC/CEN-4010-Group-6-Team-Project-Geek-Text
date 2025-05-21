import Link from "next/link";

export default function Header() {
  return (
    <header className="bg-green-800 text-white sticky top-0 flex-wrap z-[20] mx-auto flex w-full items-center justify-between border-b border-gray-500 p-5">
      <div className="logo">
        <Link href="/">Geek Text</Link>
      </div>
      <form className="max-wd mx-auto">
        <label
          htmlFor="search"
          className="mb-2 text-sm font-medium text-gray-900 sr-only"
        >
          Search
        </label>
        <div className="relative">
          <div className="absolute inset-y-0 start-0 flex items-center ps-3 pointer-events-none">
            <svg
              className="w-4 h-4 text-gray-500 dark:text-gray-400"
              aria-hidden="true"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 20 20"
            >
              <path
                stroke="currentColor"
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
                d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"
              />
            </svg>
          </div>
          <input
            type="text"
            name="search"
            placeholder="Search Book"
            autoComplete="off"
            aria-label="search"
            className="block w-full p-4 ps-10 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50"
          />
        </div>
      </form>
      <div className="flex gap-5 justify-end">
        <div className="md:flex justify-between">
          <Link href="/createbook">Create Book</Link>
        </div>
        <div className="md:flex justify-between">
          <Link href="/userauth">Signup</Link>
        </div>
      </div>
    </header>
  );
}
