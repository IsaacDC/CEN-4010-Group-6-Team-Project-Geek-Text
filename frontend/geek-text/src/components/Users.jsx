import React from "react";
import useUsers from "../hooks/useUsers";

const API = "http://localhost:8080/api/user/list";

export default function Users() {
  const { users, loading, error } = useUsers(API);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;

  return (
    <div className="flex gap-5 flex-wrap justify-center">
      {users.map((user) => (
        <div
          key={user.id}
          className="max-w-sm rounded overflow-hidden shadow-lg"
        >
          <img
            className="w-full"
            src="https://placehold.co/600x400"
            alt={`${user.fullname}'s Profile`}
          />
          <div className="px-6 py-4">
            <div className="font-bold text-xl mb-2">{user.fullname}</div>
            <p className="text-gray-700 text-base">{user.address}</p>
          </div>
          <div className="px-6 pt-4 pb-2">
            <span className="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">
              {user.username}
            </span>
          </div>
        </div>
      ))}
    </div>
  );
}
