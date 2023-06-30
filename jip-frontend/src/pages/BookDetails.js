import React, {useState} from "react"
import {useLocation} from "react-router-dom";
import BookService from "../service/BookService";
import {Button} from 'antd';
import {isEmpty} from "lodash";

const BookDetails = () => {
    const [data, setData] = useState({})
    const location = useLocation();
    const {bookId} = location.state;

    if(isEmpty(data)) {
        debugger
        const bookInfo = BookService.getBook(bookId).then(function (result) {
            setData(result.data)
        })
    }

    const handleReads = (event) => {
        debugger
        BookService.addToReads(bookId)
    }

    const handleFavourites = (event) => {
        debugger
        BookService.addToFavourites(bookId)
    }

    const {name, author, isbn} = data;

    return (
        <div>
            <div>
                <span>{name}</span>
            </div>
            <div>
                <span>{author}</span>
            </div>
            <div>
                <span>{isbn}</span>
            </div>
            <Button type="primary" onClick={handleReads}>Add to Reads</Button>
            <Button type="primary" onClick={handleFavourites}>Add to favourites</Button>
            <span>kafjdlfjaldskfjdf23</span>
            <br/>
            <span>{bookId}</span>
        </div>
    )

}

export default BookDetails
