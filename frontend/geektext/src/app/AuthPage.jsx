import React, { useState } from "react";
import { useSearchParams } from "react-router-dom";
import LogInCard from "../components/UserAuth/LogInCard";
import SignUpCard from "../components/UserAuth/SignUpCard";
const AuthPage = () => {
  const [searchParams] = useSearchParams();
  const mode = searchParams.get("mode") === "signup" ? false : true;
  const [isLogIn, setIsLogIn] = useState(mode);
  const toggleAuthMode = () => {
    setIsLogIn((prev) => !prev);
  };

  return (
    <section style={{ backgroundColor: "var(--primary-color" }}>
      <div className="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
        <a
          className="flex items-center mb-6 text-2xl font-semibold"
          style={{ color: "var(--dark-green)" }}
        >
          GeekText
        </a>
        {isLogIn ? (
          <LogInCard toggleAuthMode={toggleAuthMode} />
        ) : (
          <SignUpCard toggleAuthMode={toggleAuthMode} />
        )}
      </div>
    </section>
  );
};

export default AuthPage;
