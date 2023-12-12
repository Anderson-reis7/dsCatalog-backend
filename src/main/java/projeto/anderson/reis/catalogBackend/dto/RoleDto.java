package projeto.anderson.reis.catalogBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import projeto.anderson.reis.catalogBackend.entities.Role;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleDto {
    private Long id;
    private String authority;

    public RoleDto(Role entity) {
        this.id = entity.getId();
        this.authority = entity.getAuthority();
    }
}
