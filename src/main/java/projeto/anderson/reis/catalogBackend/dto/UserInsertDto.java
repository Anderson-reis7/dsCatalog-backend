package projeto.anderson.reis.catalogBackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInsertDto extends UserDto{
    private String password;

    public UserInsertDto() {
        super();
    }
}
