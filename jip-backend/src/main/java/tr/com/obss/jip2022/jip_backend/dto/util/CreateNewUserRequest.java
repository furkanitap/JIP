package tr.com.obss.jip2022.jip_backend.dto.util;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateNewUserRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;

    private String name;

}
