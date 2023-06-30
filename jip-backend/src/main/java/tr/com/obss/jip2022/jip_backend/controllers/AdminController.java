package tr.com.obss.jip2022.jip_backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import tr.com.obss.jip2022.jip_backend.dto.UserDto;
import tr.com.obss.jip2022.jip_backend.dto.util.PageUtil;
import tr.com.obss.jip2022.jip_backend.mappers.BookMapper;
import tr.com.obss.jip2022.jip_backend.mappers.UserMapper;
import tr.com.obss.jip2022.jip_backend.model.Book;
import tr.com.obss.jip2022.jip_backend.service.BookService;
import tr.com.obss.jip2022.jip_backend.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final BookService bookService;

    private final UserService userService;

    private final UserMapper userMapper;

    private final BookMapper bookMapper;

    @PostMapping("/createBook")
    public void createBook(@RequestBody @Valid Book book) {
        bookService.createBook(book);
    }

    @PostMapping("/createBooks")
    public void createBooks(@RequestBody @Valid List<Book> books) {
        bookService.createBooks(books);
    }

    @GetMapping("/getAllUsers")
    public List<UserDto> getAllUsers(@RequestParam PageUtil pageRequest) {
        Pageable pageable = PageRequest.of(pageRequest.getCurrent() - 1, pageRequest.getPageSize());
        return userService.getAllUsers(pageable).stream().map(userMapper::mapTo).collect(Collectors.toList());
    }

    @DeleteMapping("/deleteUser/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }

    @DeleteMapping("/deleteBook/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        bookService.deleteBookById(bookId);
    }
}


