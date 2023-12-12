package projeto.anderson.reis.catalogBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.anderson.reis.catalogBackend.entities.Role;
import projeto.anderson.reis.catalogBackend.entities.User;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
