package tr.com.obss.jip2022.jip_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.obss.jip2022.jip_backend.model.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBookByName(String name);

    List<Book> findBooksByAuthor(String author);

    void deleteBookById(Long id);

    List<Book> findBooksByNameStartsWith(String searchWord);
}
