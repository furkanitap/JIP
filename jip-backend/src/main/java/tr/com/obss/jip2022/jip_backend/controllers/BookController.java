package tr.com.obss.jip2022.jip_backend.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tr.com.obss.jip2022.jip_backend.dto.BookDto;
import tr.com.obss.jip2022.jip_backend.dto.util.SearchRequest;
import tr.com.obss.jip2022.jip_backend.mappers.BookMapper;
import tr.com.obss.jip2022.jip_backend.model.Book;
import tr.com.obss.jip2022.jip_backend.service.BookService;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    private final BookMapper bookMapper;

    @GetMapping("/getById")
    public BookDto getById(@RequestParam(name = "bookId") Long id) {
        Book book = bookService.findBookById(id);
        return bookMapper.mapTo(bookService.findBookById(id));
    }

    @GetMapping("/findByName/{name}")
    public List<BookDto> findUserByName(@PathVariable String name) {
        return bookService.findBookByName(name).stream().map(bookMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping("/getAllBooks")
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks().stream().map(bookMapper::mapTo).collect(Collectors.toList());
    }

    @PostMapping("/doSearch")
    public List<Book> doSearch(@RequestBody SearchRequest searchRequest) {
        List<Book> books = bookService.doSearch(searchRequest.getSearchParam());
        return books;
    }

}
