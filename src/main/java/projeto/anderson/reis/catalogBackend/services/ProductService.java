package projeto.anderson.reis.catalogBackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto.anderson.reis.catalogBackend.dto.ProductDto;
import projeto.anderson.reis.catalogBackend.entities.Product;
import projeto.anderson.reis.catalogBackend.repository.ProductRepository;
import projeto.anderson.reis.catalogBackend.services.exceptions.DatabaseException;
import projeto.anderson.reis.catalogBackend.services.exceptions.ResourceNotFoundException;

import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public Page<ProductDto> findAllPaged(PageRequest pageRequest) {
        Page<Product> list = repository.findAll(pageRequest);
        return list.map(x -> new ProductDto(x));
    }

    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        Optional<Product> byId = repository.findById(id);
        Product entity = byId.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado!"));
        return new ProductDto(entity, entity.getCategories());
    }

    @Transactional
    public ProductDto insert(ProductDto dto) {
        Product product = new Product();
        // product.setName(dto.getName());
        product = repository.save(product);
        return new ProductDto(product);
    }

    @Transactional
    public ProductDto update(Long id, ProductDto dto) {
        try {
            Product referenceById = repository.getReferenceById(id);
            referenceById.setName(dto.getName());
            referenceById = repository.save(referenceById);
            return new ProductDto(referenceById);
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
