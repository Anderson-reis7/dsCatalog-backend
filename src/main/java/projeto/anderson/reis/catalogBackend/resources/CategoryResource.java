package projeto.anderson.reis.catalogBackend.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto.anderson.reis.catalogBackend.entities.Category;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        // Apenas para teste
        List<Category> list = new ArrayList<>();
        list.add(new Category(1L, "Books"));
        list.add(new Category(2L, "IT"));
        list.add(new Category(3L, "School"));
        list.add(new Category(4L, "Lab"));

        return ResponseEntity.ok().body(list);
    }
}
