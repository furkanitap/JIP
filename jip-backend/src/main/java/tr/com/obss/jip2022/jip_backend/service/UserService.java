package tr.com.obss.jip2022.jip_backend.service;

import org.springframework.data.domain.Pageable;
import tr.com.obss.jip2022.jip_backend.dto.util.CreateNewUserRequest;
import tr.com.obss.jip2022.jip_backend.model.Book;
import tr.com.obss.jip2022.jip_backend.model.User;

import java.util.List;

public interface UserService {

    User findUserById(Long id);

    List<User> findUserByName(String name);

    void createUser(CreateNewUserRequest createNewUserRequest);

    void createUser(User user);

    User findUserByUsername(String username);

    List<User> getAllUsers(Pageable pageRequest);

    void addBookToFavourite(Long userId, Long bookId);

    void deleteUserById(Long userId);

    void addBookToRead(Long userId, Long bookId);

    List<Book> getFavourites(Long id);

    List<Book> getReads(Long id);
}
