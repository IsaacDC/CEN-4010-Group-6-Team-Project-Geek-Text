import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import Users from "./components/Users";
import BookDetails from "./components/BookDetails";
import Signup from "./pages/Signup";
import SignUpCard from "./components/SignUpCard";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Signup />} />
        <Route path="/home" element={<Home />} />
        <Route path="/users" element={<Users />} />
        <Route path="/book/:isbn" element={<BookDetails />} />
        <Route path="/card" element={<SignUpCard />} />
      </Routes>
    </Router>
  );
}

export default App;
