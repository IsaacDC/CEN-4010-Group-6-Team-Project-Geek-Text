import { NavLink } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faBars,
  faXmark,
  faCartShopping,
} from "@fortawesome/free-solid-svg-icons";
import React, { useState } from "react";

const Nav = () => {
  const [isOpen, setIsOpen] = useState(false);

  const toggleNavbar = () => {
    setIsOpen(!isOpen);
  };

  return (
    <>
      <nav className="flex gap-5 justify-end">
        <div className="hidden md:flex justify-between">
          <NavLink to="/?mode=signup">Signup</NavLink>
        </div>
        <div>
          <FontAwesomeIcon icon={faCartShopping} />
        </div>
        <div className="md:hidden">
          <button onClick={toggleNavbar}>
            {isOpen ? (
              <FontAwesomeIcon icon={faXmark} />
            ) : (
              <FontAwesomeIcon icon={faBars} />
            )}
          </button>
        </div>
      </nav>

      {isOpen && (
        <div>
          <ul className="flex flex-col items-center basis-full">
            <li>
              <NavLink to="/signup">Signup</NavLink>
            </li>
          </ul>
        </div>
      )}
    </>
  );
};

export default Nav;
