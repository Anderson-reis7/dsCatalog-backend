package projeto.anderson.reis.catalogBackend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto.anderson.reis.catalogBackend.dto.CategoryDto;
import projeto.anderson.reis.catalogBackend.services.CategoryService;


import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
    @Autowired
    private CategoryService service;
    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll(){
        List<CategoryDto> dtos = service.findAll();
        return ResponseEntity.ok().body(dtos);
    }
}
