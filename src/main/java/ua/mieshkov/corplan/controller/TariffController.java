package ua.mieshkov.corplan.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.mieshkov.corplan.dto.TariffDTO;
import ua.mieshkov.corplan.model.Tariff;
import ua.mieshkov.corplan.model.User;
import ua.mieshkov.corplan.service.TariffService;
import ua.mieshkov.corplan.service.UserService;
import ua.mieshkov.corplan.util.GeneratePdf;
import ua.mieshkov.corplan.util.ValidationErrorUtil;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Locale;

@Slf4j
@Controller
public class TariffController {

    final TariffService tariffService;
    final UserService userService;

    public TariffController(TariffService tariffService, UserService userService) {
        this.tariffService = tariffService;
        this.userService = userService;
    }

    @GetMapping("/tariff-list/{service}")
    public String tariffMenu(
            @AuthenticationPrincipal org.springframework.security.core.userdetails.User currentUser,
                             @PathVariable String service,
                             @PageableDefault(sort={"nameEn"}, direction = Sort.Direction.ASC, size = 5) Pageable pageable,
                             Model model) {
        Page<Tariff> page = tariffService.findByService(service, pageable);
        if (currentUser.getAuthorities().contains(new SimpleGrantedAuthority("CLIENT"))) {
            User user = userService.findById(Long.parseLong(currentUser.getUsername()));
            model.addAttribute("balance", user.getBalance());
            model.addAttribute("usersTariffs", user.getUsersTariffs());
        }
        model.addAttribute("page", page);
        model.addAttribute("service", service);
        return "/tariff-list";
    }

    @GetMapping(value = "/pdf/{service}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> tariffPDF(@PathVariable String service) {

        Locale locale = LocaleContextHolder.getLocale();
        List<Tariff> tariffs = tariffService.findByService(service);

        ByteArrayInputStream bis = GeneratePdf.tariffList(tariffs, locale);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=tariffs.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/admin/tariff-edit/{id}")
    public String tariffEditForm(@PathVariable Long id, Model model) {
        Tariff tariff = tariffService.findById(id);
        model.addAttribute("tariff", tariff);

        return "/admin/tariff-edit";
    }

    @PostMapping("/admin/tariff-edit/{id}")
    public String tariffEdit(@PathVariable Long id, @RequestParam String nameEn,
                             @RequestParam String nameUkr, @RequestParam String price) {
        Tariff tariff = tariffService.findById(id);
        if(!nameEn.isEmpty()) {
            tariff.setNameEn(nameEn);
        }
        if(!nameUkr.isEmpty()) {
            tariff.setNameUkr(nameUkr);
        }
        if(!price.isEmpty()) {
            tariff.setPrice(Double.parseDouble(price));
        }
        tariffService.updateTariff(tariff);
        return "redirect:/tariff-list/"  +  tariff.getService();
    }

    @GetMapping("admin/tariff-create/{service}")
    public String tariffCreateForm(@PathVariable String service, Model model) {
        model.addAttribute("service", service);
        return "admin/tariff-create";
    }

    @PostMapping("admin/tariff-create/{service}")
    public String createTariff(@Valid TariffDTO tariffDTO, BindingResult result,
                               @PathVariable String service, Errors errors, Model model) {
        if(result.hasErrors()) {
            model.mergeAttributes(ValidationErrorUtil.getErrorsMap(errors));
            model.addAttribute("tariffDTO", tariffDTO);
            return "/admin/tariff-create";
        }
         tariffDTO.setService(service);
        tariffService.saveTariff(tariffDTO);
        log.info("New tariff " + tariffDTO.getNameEn() + " created");
        return "redirect:/tariff-list/" + service;
    }
}
