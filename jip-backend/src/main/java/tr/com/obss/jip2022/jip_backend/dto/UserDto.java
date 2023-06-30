package tr.com.obss.jip2022.jip_backend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class UserDto {

    @NotNull
    private Long id;

    private String name;

    private Set<BookDto> favouriteList;

    private Set<BookDto> readList;

}
