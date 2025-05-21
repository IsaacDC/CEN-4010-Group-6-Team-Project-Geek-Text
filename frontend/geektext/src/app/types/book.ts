
export type Author = {
  firstName: string;
  lastName: string;
};

export interface Book {
  id?: number;
  title: string;
  author: Author;
  genre: string;
  description: string;
  yearPublished: number;
  isbn: number;
  price: number;
  copiesSold: number;
  coverImage?: string;
};