package projeto.anderson.reis.catalogBackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto.anderson.reis.catalogBackend.config.exeption.EntityNotFoundException;
import projeto.anderson.reis.catalogBackend.dto.CategoryDto;
import projeto.anderson.reis.catalogBackend.entities.Category;
import projeto.anderson.reis.catalogBackend.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;
    @Transactional(readOnly = true)
    public List<CategoryDto> findAll(){
        List<Category> list = repository.findAll();
        return list.stream()
                .map(CategoryDto::new)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public CategoryDto findById(Long id){
        Optional<Category> byId = repository.findById(id);
        Category entity = byId.orElseThrow(() -> new EntityNotFoundException("Id não encontrado!"));
        return new CategoryDto(entity);
    }
    @Transactional(readOnly = true)
    public CategoryDto insert(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category = repository.save(category);
        return new CategoryDto(category);
    }
}
