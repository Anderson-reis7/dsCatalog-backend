package projeto.anderson.reis.catalogBackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto.anderson.reis.catalogBackend.dto.CategoryDto;
import projeto.anderson.reis.catalogBackend.entities.Category;
import projeto.anderson.reis.catalogBackend.repository.CategoryRepository;

import java.util.List;
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
}
