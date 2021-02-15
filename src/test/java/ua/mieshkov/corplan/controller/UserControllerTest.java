package ua.mieshkov.corplan.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;
import ua.mieshkov.corplan.exception.UserNotFoundException;
import ua.mieshkov.corplan.model.User;
import ua.mieshkov.corplan.model.UserStatus;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = { "/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void getUserById() throws Exception {
        this.mockMvc.perform(get("/admin/user-details/100001"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("user",
                        Matchers.hasProperty("id", Matchers.equalTo(100001L))))
                .andExpect(model().attribute("user",
                        Matchers.hasProperty("nameEn",
                                Matchers.equalToIgnoringCase("John Anderson"))))
                .andExpect(model().attribute("user",
                        Matchers.hasProperty("email",
                                Matchers.equalToIgnoringCase("john.a@gmail.com"))));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void getUserByIdNotExists() throws Exception {
        try {
            this.mockMvc.perform(get("/admin/user-details/100003"))
                    .andExpect(result -> assertTrue(result.getResolvedException() instanceof UserNotFoundException));
        } catch (NestedServletException e) {
            e.getMessage();
        }
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void getAllUsers() throws Exception {
        this.mockMvc.perform(get("/admin/menu"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("page",
                        Matchers.hasItems(
                                Matchers.<User>hasProperty("nameUkr",
                                        Matchers.equalToIgnoringCase("Джон Андерсон")),
                                Matchers.<User>hasProperty("nameUkr",
                                        Matchers.equalToIgnoringCase("Адмін")))));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void changeStatus() throws Exception {
        this.mockMvc.perform(post("/admin/user-status/100001"))
                .andExpect(redirectedUrl("/admin/menu"));

        this.mockMvc.perform(get("/admin/user-details/100001"))
                .andExpect(model().attribute("user",
                        Matchers.hasProperty("userStatus", Matchers.is(UserStatus.BLOCKED))));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void createUser() throws Exception {
        this.mockMvc.perform(post("/registration")
        .param("nameEn", "Serhii Fedorov")
        .param("nameUkr", "Сергій Федоров")
        .param("phone", "+380556971463")
        .param("password", "123456")
        .param("email", "fedorov.s@ukr.net")
        .param("address", "Kyiv, Kudri st. 56, apt.4"))
        .andExpect(redirectedUrl("/admin/menu"));

        this.mockMvc.perform(get("/admin/menu"))
                .andExpect(model().attribute("page",
                        Matchers.hasItems(
                                Matchers.<User>hasProperty("nameEn",
                                        Matchers.equalToIgnoringCase("Serhii Fedorov")),
                                Matchers.<User>hasProperty("nameUkr",
                                        Matchers.equalToIgnoringCase("Сергій Федоров")),
                                Matchers.<User>hasProperty("phone",
                                        Matchers.equalToIgnoringCase("+380556971463")),
                                Matchers.<User>hasProperty("email",
                                        Matchers.equalToIgnoringCase("fedorov.s@ukr.net")),
                                Matchers.<User>hasProperty("address",
                                        Matchers.equalToIgnoringCase("Kyiv, Kudri st. 56, apt.4")))));
    }
}
