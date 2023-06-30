package tr.com.obss.jip2022.jip_backend.service;


import tr.com.obss.jip2022.jip_backend.model.Book;

import java.util.List;

public interface BookService {

    Book findBookById(Long id);

    List<Book> findBookByName(String name);

    void createBook(Book book);

    List<Book> getAllBooks();

    List<Book> findBooksByAuthor(String author);

    void deleteBookById(Long bookId);

    void createBooks(List<Book> books);

    List<Book> doSearch(String searchWord);
}
