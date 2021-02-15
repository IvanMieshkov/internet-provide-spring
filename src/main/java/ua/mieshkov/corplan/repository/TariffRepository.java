package ua.mieshkov.corplan.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.mieshkov.corplan.model.Tariff;

import java.util.List;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Long> {

    Page<Tariff> findByService(String service, Pageable pageable);
    List<Tariff> findByService(String service);
}
