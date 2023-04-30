import axios from "../custom-axios/axios";

const LibraryService = {
    fetchAuthors: () => {
        return axios.get("/authors/list");
    },
    fetchCategories: () => {
        return axios.get("/categories");
    },
    fetchBooks: () => {
        return axios.get("/books/list");
    },
    addBook: (name, category, authorId, availableCopies) => {
        return axios.post("/books/add", {
            name: name,
            category: category,
            authorId: authorId,
            availableCopies: availableCopies,
        });
    },
    editBook: (id, name, category, authorId, availableCopies) => {
        return axios.put(`/books/edit/${id}`, {
            name: name,
            category: category,
            authorId: authorId,
            availableCopies: availableCopies,
        });
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    },
    markAsTaken: (id) => {
        return axios.put(`/books/take/${id}`);
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
};

export default LibraryService;