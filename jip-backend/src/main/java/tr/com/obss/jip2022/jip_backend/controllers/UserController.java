package tr.com.obss.jip2022.jip_backend.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import tr.com.obss.jip2022.jip_backend.dto.UserDto;
import tr.com.obss.jip2022.jip_backend.dto.util.SearchRequest;
import tr.com.obss.jip2022.jip_backend.mappers.UserMapper;
import tr.com.obss.jip2022.jip_backend.model.Book;
import tr.com.obss.jip2022.jip_backend.service.UserService;
import tr.com.obss.jip2022.jip_backend.service.impl.UserServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping("/getById/{id}")
    public UserDto getById(@PathVariable Long id) {
        return userMapper.mapTo(userService.findUserById(id));
    }

    @GetMapping("/findByName/{name}")
    public List<UserDto> findUserByName(@PathVariable String name) {
        return userService.findUserByName(name).stream().map(userMapper::mapTo).collect(Collectors.toList());
    }

    @PutMapping("/addToFavourite")
    public void addBookToFavorite(@RequestBody SearchRequest searchRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userService.findUserByUsername(user.getUsername()).getId();
        userService.addBookToFavourite(userId, searchRequest.getBookId());
    }

    @PutMapping("/addToRead")
    public void addBookToRead(@RequestBody SearchRequest searchRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userService.findUserByUsername(user.getUsername()).getId();
        userService.addBookToRead(userId, searchRequest.getBookId());
    }

    @GetMapping("/getFavourites")
    public List<Book> getFavorites() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userService.findUserByUsername(user.getUsername()).getId();
        return userService.getFavourites(userId);
    }

    @GetMapping("/getReads")
    public List<Book> getReads() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userService.findUserByUsername(user.getUsername()).getId();
        return userService.getReads(userId);
    }

}
