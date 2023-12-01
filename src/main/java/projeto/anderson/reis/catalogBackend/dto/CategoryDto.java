package projeto.anderson.reis.catalogBackend.dto;

import lombok.Getter;
import lombok.Setter;
import projeto.anderson.reis.catalogBackend.entities.Category;
@Getter
@Setter
public class CategoryDto {
    private Long id;
    private String name;

    public CategoryDto() {
    }

    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDto(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
