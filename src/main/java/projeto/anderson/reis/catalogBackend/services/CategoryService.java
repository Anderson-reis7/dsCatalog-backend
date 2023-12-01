package projeto.anderson.reis.catalogBackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto.anderson.reis.catalogBackend.entities.Category;
import projeto.anderson.reis.catalogBackend.repository.CategoryRepository;

import java.util.List;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;
    @Transactional(readOnly = true)
    public List<Category> findAll(){
        return repository.findAll();
    }
}
