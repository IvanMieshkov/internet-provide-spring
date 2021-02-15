package ua.mieshkov.corplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.mieshkov.corplan.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
