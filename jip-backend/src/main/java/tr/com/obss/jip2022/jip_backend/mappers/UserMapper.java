package tr.com.obss.jip2022.jip_backend.mappers;


import org.mapstruct.Mapper;
import tr.com.obss.jip2022.jip_backend.dto.UserDto;
import tr.com.obss.jip2022.jip_backend.dto.util.CreateNewUserRequest;
import tr.com.obss.jip2022.jip_backend.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto mapTo(User user);

    User mapTo(CreateNewUserRequest createNewUserRequest);
}
