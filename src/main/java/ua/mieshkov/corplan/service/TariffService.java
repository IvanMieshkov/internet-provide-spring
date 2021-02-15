package ua.mieshkov.corplan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.mieshkov.corplan.dto.TariffDTO;
import ua.mieshkov.corplan.exception.TariffAlreadyExistsException;
import ua.mieshkov.corplan.exception.TariffNotFoundException;
import ua.mieshkov.corplan.model.Tariff;
import ua.mieshkov.corplan.repository.TariffRepository;

import java.util.List;

@Slf4j
@Service
public class TariffService {

    private final TariffRepository tariffRepository;

    @Autowired
    public TariffService(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    public Tariff findById(Long id) {
        return tariffRepository.findById(id)
                .orElseThrow(() -> new TariffNotFoundException("Tariff with id " + id + " not found"));
    }

    public Page<Tariff> findByService(String service, Pageable pageable) {
        return tariffRepository.findByService(service, pageable);
    }

    public List<Tariff> findByService(String service) {
        return tariffRepository.findByService(service);
    }

    public void updateTariff(Tariff tariff) {
        tariffRepository.save(tariff);
    }

    public void saveTariff(TariffDTO tariffDTO) {
        Tariff tariff = Tariff.builder()
                .nameEn(tariffDTO.getNameEn())
                .nameUkr(tariffDTO.getNameUkr())
                .price(Double.parseDouble(tariffDTO.getPrice()))
                .service(tariffDTO.getService())
                .build();
        try {
            tariffRepository.save(tariff);
        } catch (DataIntegrityViolationException e) {
            throw new TariffAlreadyExistsException("Tariff already exists!");
        }
    }
}

