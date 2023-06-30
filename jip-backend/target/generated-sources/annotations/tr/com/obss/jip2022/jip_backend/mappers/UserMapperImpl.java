package tr.com.obss.jip2022.jip_backend.mappers;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tr.com.obss.jip2022.jip_backend.dto.BookDto;
import tr.com.obss.jip2022.jip_backend.dto.UserDto;
import tr.com.obss.jip2022.jip_backend.dto.util.CreateNewUserRequest;
import tr.com.obss.jip2022.jip_backend.model.Book;
import tr.com.obss.jip2022.jip_backend.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-23T18:15:03+0300",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 13.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto mapTo(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setName( user.getName() );
        userDto.setFavouriteList( bookSetToBookDtoSet( user.getFavouriteList() ) );
        userDto.setReadList( bookSetToBookDtoSet( user.getReadList() ) );

        return userDto;
    }

    @Override
    public User mapTo(CreateNewUserRequest createNewUserRequest) {
        if ( createNewUserRequest == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( createNewUserRequest.getUsername() );
        user.setPassword( createNewUserRequest.getPassword() );
        user.setName( createNewUserRequest.getName() );

        return user;
    }

    protected BookDto bookToBookDto(Book book) {
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

    protected Set<BookDto> bookSetToBookDtoSet(Set<Book> set) {
        if ( set == null ) {
            return null;
        }

        Set<BookDto> set1 = new LinkedHashSet<BookDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Book book : set ) {
            set1.add( bookToBookDto( book ) );
        }

        return set1;
    }
}
