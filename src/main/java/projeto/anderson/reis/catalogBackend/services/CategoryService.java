package projeto.anderson.reis.catalogBackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto.anderson.reis.catalogBackend.services.exceptions.DatabaseException;
import projeto.anderson.reis.catalogBackend.services.exceptions.ResourceNotFoundException;
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
    public List<CategoryDto> findAll() {
        List<Category> list = repository.findAll();
        return list.stream()
                .map(CategoryDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDto findById(Long id) {
        Optional<Category> byId = repository.findById(id);
        Category entity = byId.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado!"));
        return new CategoryDto(entity);
    }

    @Transactional
    public CategoryDto insert(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category = repository.save(category);
        return new CategoryDto(category);
    }

    @Transactional
    public CategoryDto update(Long id, CategoryDto dto) {
        try {
            Category referenceById = repository.getReferenceById(id);
            referenceById.setName(dto.getName());
            referenceById = repository.save(referenceById);
            return new CategoryDto(referenceById);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id " + id + " não encontrado!");
        }
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Id " + id + " não encontrado!");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade!");
        }
    }
}
