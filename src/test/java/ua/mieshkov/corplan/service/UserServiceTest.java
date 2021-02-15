package ua.mieshkov.corplan.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ua.mieshkov.corplan.model.User;
import ua.mieshkov.corplan.repository.UserRepository;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAll() {
        Assert.assertEquals(2, userRepository.findAll().size());
    }

    @Test
    public void findById() {
        Optional<User> user = userRepository.findById(100000L);
        Assert.assertEquals("Admin", user.get().getNameEn());
    }

    @Test
    public void deleteById() {
        userRepository.deleteById(100001L);
        Assert.assertEquals(1, userRepository.findAll().size());
    }
}