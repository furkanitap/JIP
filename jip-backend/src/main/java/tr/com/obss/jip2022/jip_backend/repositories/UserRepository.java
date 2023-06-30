package tr.com.obss.jip2022.jip_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tr.com.obss.jip2022.jip_backend.model.Book;
import tr.com.obss.jip2022.jip_backend.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

    List<User> findUserByName(String name);

    void deleteUserById(Long id);

    @Query("select u.favouriteList from User u where u.id = ?1")
    List<Book> getFavourites(Long id);

    @Query("select u.readList from User u where u.id = ?1")
    List<Book> getReads(Long id);
}
