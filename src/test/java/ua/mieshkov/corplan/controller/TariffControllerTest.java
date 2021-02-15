package ua.mieshkov.corplan.controller;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.mieshkov.corplan.model.Tariff;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-tariff-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-tariff-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class TariffControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void getTariffsByService() throws Exception {
        this.mockMvc.perform(get("/tariff-list/internet"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("page",
                        Matchers.hasItems(
                            Matchers.<Tariff>hasProperty("nameEn",
                                    Matchers.equalToIgnoringCase("Basic")),
                            Matchers.<Tariff>hasProperty("nameEn",
                                    Matchers.equalToIgnoringCase("Fast")))));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void createTariff() throws Exception {
        this.mockMvc.perform(post("/admin/tariff-create/tv")
                .param("nameEn", "Local")
                .param("nameUkr", "Місцевий")
                .param("price", "80"))
                .andExpect(redirectedUrl("/tariff-list/tv"));

        this.mockMvc.perform(get("/tariff-list/tv"))
                .andExpect(model().attribute("page",
                        Matchers.hasItems(
                                Matchers.<Tariff>hasProperty("nameEn",
                                        Matchers.equalToIgnoringCase("Local")),
                                Matchers.<Tariff>hasProperty("nameUkr",
                                        Matchers.equalToIgnoringCase("Місцевий")),
                                Matchers.<Tariff>hasProperty("price",
                                        Matchers.is(80.0)))));
    }
}
