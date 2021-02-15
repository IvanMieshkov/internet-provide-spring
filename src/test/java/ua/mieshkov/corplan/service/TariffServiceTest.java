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
import ua.mieshkov.corplan.model.Tariff;
import ua.mieshkov.corplan.repository.TariffRepository;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-tariff-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-tariff-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class TariffServiceTest {
    @Autowired
    private TariffRepository tariffRepository;

    @Test
    public void findById() {
        Optional<Tariff> tariff = tariffRepository.findById(2L);
        Assert.assertEquals("Fast", tariff.get().getNameEn());
    }

    @Test
    public void findAll() {
        Assert.assertEquals(4, tariffRepository.findAll().size());
    }

    @Test
    public void findByService() {
        List<Tariff> tariffs = tariffRepository.findByService("internet");
        Assert.assertEquals(2, tariffs.size());
    }
}