package tr.com.obss.jip2022.jip_backend.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.obss.jip2022.jip_backend.dto.util.CreateNewUserRequest;
import tr.com.obss.jip2022.jip_backend.exceptions.UsernameAlreadyExists;
import tr.com.obss.jip2022.jip_backend.exceptions.notFoundExceptions.UserNotFoundException;
import tr.com.obss.jip2022.jip_backend.mappers.UserMapper;
import tr.com.obss.jip2022.jip_backend.model.Book;
import tr.com.obss.jip2022.jip_backend.model.Role;
import tr.com.obss.jip2022.jip_backend.model.User;
import tr.com.obss.jip2022.jip_backend.model.enums.RoleType;
import tr.com.obss.jip2022.jip_backend.repositories.UserRepository;
import tr.com.obss.jip2022.jip_backend.service.BookService;
import tr.com.obss.jip2022.jip_backend.service.RoleService;
import tr.com.obss.jip2022.jip_backend.service.UserService;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    private final BookService bookService;

    @Override
    public User findUserById(Long id) {
        Objects.requireNonNull(id, "ID cannot be null");
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with ID: %d" + id));
    }

    public List<User> findUserByName(String name) {
        Objects.requireNonNull(name, "Name cannot be null");
        List<User> users = userRepository.findUserByName(name);
        if (!users.isEmpty()) {
            return users;
        } else {
            throw new UserNotFoundException(String.format("No Users with name %s was found.", name));
        }
    }

    @Override
    public void createUser(CreateNewUserRequest createNewUserRequest) {
        final User user = userMapper.mapTo(createNewUserRequest);
        final Optional<User> existingUser = userRepository.findUserByUsername(createNewUserRequest.getUsername());

        if (existingUser.isPresent()) {
            throw new UsernameAlreadyExists(String.format("User with username %s already exists.",
                    createNewUserRequest.getUsername()));
        }

        user.setPassword(passwordEncoder.encode(createNewUserRequest.getPassword()));

        user.setFavouriteList(new HashSet<>());
        user.setReadList(new HashSet<>());

        final Role standardRole = roleService.findByName(RoleType.ROLE_USER);
        user.setRoles(Set.of(standardRole));

        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteUserById(userId);
    }

    @Override
    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setFavouriteList(new HashSet<>());
        user.setReadList(new HashSet<>());
        userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        Objects.requireNonNull(username, "Username cannot be null");
        return userRepository.findUserByUsername(username).orElseThrow(() ->
                new UserNotFoundException(String.format("User not found with username: %s", username)));
    }

    @Override
    public List<User> getAllUsers(Pageable pageable) {
        List<User> users = new ArrayList<>();
        userRepository.findAll(pageable).forEach(users::add);
        return users;
    }

    @Override
    public void addBookToFavourite(Long userId, Long bookId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("User not found with ID: " + userId));
        Book book = bookService.findBookById(bookId);

        user.getFavouriteList().add(book);
        userRepository.save(user);
    }

    @Override
    public void addBookToRead(Long userId, Long bookId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("User not found with ID: " + userId));
        Book book = bookService.findBookById(bookId);

        user.getReadList().add(book);
        userRepository.save(user);
    }

    @Override
    public List<Book> getFavourites(Long id) {
        return userRepository.getFavourites(id);
    }

    @Override
    public List<Book> getReads(Long id) {
        return userRepository.getReads(id);
    }
}
