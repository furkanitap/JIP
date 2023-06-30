package tr.com.obss.jip2022.jip_backend.mappers;


import org.mapstruct.Mapper;
import tr.com.obss.jip2022.jip_backend.dto.BookDto;
import tr.com.obss.jip2022.jip_backend.model.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto mapTo(Book book);

    Book mapFrom(BookDto bookDto);
}
