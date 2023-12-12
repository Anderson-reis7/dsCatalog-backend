package projeto.anderson.reis.catalogBackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto.anderson.reis.catalogBackend.dto.RoleDto;
import projeto.anderson.reis.catalogBackend.dto.UserDto;
import projeto.anderson.reis.catalogBackend.dto.UserInsertDto;
import projeto.anderson.reis.catalogBackend.entities.Role;
import projeto.anderson.reis.catalogBackend.entities.User;
import projeto.anderson.reis.catalogBackend.repository.RoleRepository;
import projeto.anderson.reis.catalogBackend.repository.UserRepository;
import projeto.anderson.reis.catalogBackend.services.exceptions.DatabaseException;
import projeto.anderson.reis.catalogBackend.services.exceptions.ResourceNotFoundException;

import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository repository;
    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Page<UserDto> findAllPaged(Pageable pageable) {
        Page<User> list = repository.findAll(pageable);
        return list.map(UserDto::new);
    }

    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        Optional<User> byId = repository.findById(id);
        User entity = byId.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado!"));
        return new UserDto(entity);
    }

    @Transactional
    public UserDto insert(UserInsertDto dto) {
        User user = new User();
        copyDtoToEntity(dto, user);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user = repository.save(user);
        return new UserDto(user);
    }

    @Transactional
    public UserDto update(Long id, UserDto dto) {
        try {
            User referenceById = repository.getReferenceById(id);
            copyDtoToEntity(dto, referenceById);
            referenceById = repository.save(referenceById);
            return new UserDto(referenceById);
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

    private void copyDtoToEntity(UserDto dto, User entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.getRoles().clear();
        for (RoleDto roleDto : dto.getRoles()) {
            Role role = roleRepository.getReferenceById(roleDto.getId());
            entity.getRoles().add(role);
        }
    }
}
