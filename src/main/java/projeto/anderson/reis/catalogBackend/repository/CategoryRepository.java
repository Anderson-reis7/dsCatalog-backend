package projeto.anderson.reis.catalogBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.anderson.reis.catalogBackend.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
