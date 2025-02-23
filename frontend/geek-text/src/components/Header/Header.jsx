import Logo from "./Logo";
import Nav from "./Nav";
import SearchBar from "./SearchBar";

export default function Header() {
  return (
    <header className="bg-green-800 text-white sticky top-0 flex-wrap z-[20] mx-auto flex w-full items-center justify-between border-b border-gray-500 p-8">
      <Logo />
      <SearchBar />
      <Nav />
    </header>
  );
}
