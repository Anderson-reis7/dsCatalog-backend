package projeto.anderson.reis.catalogBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.anderson.reis.catalogBackend.entities.Product;
import projeto.anderson.reis.catalogBackend.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
