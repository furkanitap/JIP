package tr.com.obss.jip2022.jip_backend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tr.com.obss.jip2022.jip_backend.exceptions.notFoundExceptions.UserNotFoundException;
import tr.com.obss.jip2022.jip_backend.model.Book;
import tr.com.obss.jip2022.jip_backend.repositories.BookRepository;
import tr.com.obss.jip2022.jip_backend.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book findBookById(Long id) {
        Objects.requireNonNull(id, "ID cannot be null");
        return bookRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found with ID: %d" + id));
    }

    @Override
    public List<Book> findBookByName(String name) {
        Objects.requireNonNull(name, "Name cannot be null");
        List<Book> books = bookRepository.findBookByName(name);
        if (!books.isEmpty()) {
            return books;
        } else {
            throw new UserNotFoundException(String.format("No Users with name %s was found.", name));
        }
    }

    @Override
    public void createBook(Book book) {
        Objects.requireNonNull(book.getName(), "Book name cannot be empty.");
        bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Long bookId) {
        bookRepository.deleteBookById(bookId);
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> users = new ArrayList<>();
        bookRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        Objects.requireNonNull(author, "Author Name cannot be null");
        List<Book> books = bookRepository.findBooksByAuthor(author);
        if (!books.isEmpty()) {
            return books;
        } else {
            throw new UserNotFoundException(String.format("No Books from the author %s was found.", author));
        }
    }

    @Override
    public void createBooks(List<Book> books) {
        bookRepository.saveAll(books);
    }

    @Override
    public List<Book> doSearch(String searchWord) {
        return bookRepository.findBooksByNameStartsWith(searchWord);
    }

}
