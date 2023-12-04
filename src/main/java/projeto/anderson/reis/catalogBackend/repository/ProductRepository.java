package projeto.anderson.reis.catalogBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.anderson.reis.catalogBackend.entities.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
