import React from "react"
import BookService from "../service/BookService";

class BookDetailsV2 extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            data: []
        }
    }

    componentDidMount() {
        fetch();
    }


    fetch = (async function () {
        debugger
        const bookId = this.location.state.bookId
        const bookInfo = await BookService.getBook(bookId);
        this.setState({data: bookInfo})
        debugger
    })();


    render() {
        const {name, author, isbn} = this.state.data.bookInfo;
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
                <span>kafjdlfjaldskfjdf</span>
                <br/>

            </div>
        )
    }
}

export default BookDetailsV2