package ua.mieshkov.corplan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.mieshkov.corplan.dto.UserDTO;
import ua.mieshkov.corplan.exception.UserAlreadyExistsException;
import ua.mieshkov.corplan.exception.UserNotFoundException;
import ua.mieshkov.corplan.model.Role;
import ua.mieshkov.corplan.model.User;
import ua.mieshkov.corplan.model.UserStatus;
import ua.mieshkov.corplan.repository.UserRepository;

import java.util.List;

@Slf4j
@Service
public class UserService {

    final PasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findById(Long id) {
        log.info("IN UserService findById {}" ,id);
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    public List<User> findAll() {
        log.info("IN UserService findAll");
        return userRepository.findAll(); }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public void saveUser(UserDTO userDTO) {
        User user = User.builder()
                .nameEn(userDTO.getNameEn())
                .nameUkr(userDTO.getNameUkr())
                .phone(userDTO.getPhone())
                .password(bCryptPasswordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .address(userDTO.getAddress())
                .balance(0.00)
                .userStatus(UserStatus.ACTIVE)
                .role(Role.CLIENT)
                .build();
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException("User already exists!");
        }
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        log.info("FAILED: IN UserService deleteById {}", id);
    }
}
