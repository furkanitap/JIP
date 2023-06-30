package tr.com.obss.jip2022.jip_backend.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tr.com.obss.jip2022.jip_backend.dto.BookDto;
import tr.com.obss.jip2022.jip_backend.model.Book;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-23T18:15:03+0300",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 13.0.2 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDto mapTo(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        bookDto.setId( book.getId() );
        bookDto.setName( book.getName() );
        bookDto.setIsbn( book.getIsbn() );
        bookDto.setAuthor( book.getAuthor() );

        return bookDto;
    }

    @Override
    public Book mapFrom(BookDto bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        Book book = new Book();

        book.setName( bookDto.getName() );
        book.setIsbn( bookDto.getIsbn() );
        book.setAuthor( bookDto.getAuthor() );

        return book;
    }
}
