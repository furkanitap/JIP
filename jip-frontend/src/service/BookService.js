import axios from "axios";

const BookService = (function () {
    const _fetchFavouriteList = async (params) => {
        debugger
        const response = await axios.get("http://localhost:8080/api/user/getFavourites", {
            params: {
                ...params
            }
        });

        if (!response) {
            console.log("Bir hata oluştu");
            //ToDo: Display error message to user not just log it
            //Ex: https://www.npmjs.com/package/react-toastify
            return;
        }
        return response.data;
    };

    const _fetchReadList = async (params) => {
        const response = await axios.get("http://localhost:8080/api/user/getReads", {
            params: {
                ...params
            }
        });

        if (!response) {
            console.log("Bir hata oluştu");
            //ToDo: Display error message to user not just log it
            //Ex: https://www.npmjs.com/package/react-toastify
            return;
        }

        return response.data;
    };

    const _doSearch = async (searchParam) => {
        const response = await axios.post("http://localhost:8080/api/book/doSearch", {
            searchParam
        });

        if (!response) {
            console.log("Bir hata oluştu");
            //ToDo: Display error message to user not just log it
            //Ex: https://www.npmjs.com/package/react-toastify
            return;
        }

        return response.data;
    };

    const _getBook = (bookId) => {
        return axios.get("http://localhost:8080/api/book/getById", {
            params: {bookId: bookId}
        });
    }

    const _addToReads = (bookId) => {
        return axios.put("http://localhost:8080/api/user/addToRead", {
            bookId: bookId
        });
    }

    const _addToFavourites = (bookId) => {
        return axios.put("http://localhost:8080/api/user/addToFavourite", {
            bookId: bookId
        });
    }

    const _create = async (credentials) => {
        debugger

        try {
            const response = await axios.post(
                "http://localhost:8080/api/admin/createBook",
                credentials
            );

        } catch (error) {
            console.log(error);
        }

        return "User created";
    };

    return {
        fetchFavouriteList: _fetchFavouriteList,
        fetchReadList: _fetchReadList,
        doSearch: _doSearch,
        getBook: _getBook,
        addToReads: _addToReads,
        addToFavourites: _addToFavourites,
        create: _create
    };
})();

export default BookService;

