package ua.mieshkov.corplan.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.mieshkov.corplan.dto.UserDTO;
import ua.mieshkov.corplan.model.Tariff;
import ua.mieshkov.corplan.model.User;
import ua.mieshkov.corplan.model.UserStatus;
import ua.mieshkov.corplan.service.TariffService;
import ua.mieshkov.corplan.service.UserService;
import ua.mieshkov.corplan.util.ValidationErrorUtil;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;

@Slf4j
@Controller
public class UserController {

    private final UserService userService;
    private final TariffService tariffService;

    @Autowired
    public UserController(UserService userService, TariffService tariffService) {
        this.userService = userService;
        this.tariffService = tariffService;
    }

    @GetMapping("/registration")
    public String registrationRedirect() {
        return "/registration";
    }

    @PostMapping("/registration")
    public String createUser(@Valid UserDTO userDTO, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.mergeAttributes(ValidationErrorUtil.getErrorsMap(errors));
            model.addAttribute("userDTO", userDTO);
            return "/registration";
        }
        userService.saveUser(userDTO);
        log.info("New user " + userDTO.getNameEn() + " created");
        return "redirect:/admin/menu";
    }

    @GetMapping(value={"/client/menu", "/client/payment"})
    public String userMenu(@AuthenticationPrincipal org.springframework.security.core.userdetails.User currentUser,
                           Model model) {
        User user = userService.findById(Long.parseLong(currentUser.getUsername()));
        model.addAttribute("tariffs", user.getUsersTariffs());
        model.addAttribute("user", user);
    return "/client-menu";
    }

    @PostMapping("/client/payment")
    public String topUpBalance(@AuthenticationPrincipal org.springframework.security.core.userdetails.User currentUser,
                               @RequestParam double payment) {
        User user = userService.findById(Long.parseLong(currentUser.getUsername()));
        if(!(payment <0)) {
            payment += user.getBalance();
            user.setBalance(payment);
            if(payment>0) {
                user.setUserStatus(UserStatus.ACTIVE);
            }
            userService.updateUser(user);
        }
        return "redirect:/client/menu";
    }

    @GetMapping("client/add-tariff")
    public String userMenuRedirect() {
        return "/client-menu";
    }

    @PostMapping("client/add-tariff/{id}")
    public String addUserTariff(@AuthenticationPrincipal org.springframework.security.core.userdetails.User currentUser,
                                @PathVariable Long id, RedirectAttributes redirectAttrs) {
        Locale locale = LocaleContextHolder.getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
        Tariff tariff = tariffService.findById(id);
        User user = userService.findById(Long.parseLong(currentUser.getUsername()));
        if(user.getBalance() < 0) {
            String errorMessage = (String) bundle.getObject("warn.low.balance");
            redirectAttrs.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/tariff-list/" + tariff.getService();
        }
        user.getUsersTariffs().removeIf(tar -> tar.getService().equals(tariff.getService()));
        user.getUsersTariffs().add(tariff);
        double balance = user.getBalance()-tariff.getPrice();
        user.setBalance(balance);
        if(balance < 0) {
            user.setUserStatus(UserStatus.BLOCKED);
        }
        userService.updateUser(user);
        return "redirect:/client/menu";
    }

    @GetMapping(value={"/admin/menu", "/admin/user-status/"})
    public String getAllUsers(@PageableDefault(size = 5) Pageable pageable, Model model) {
        Page<User> page = userService.findAll(pageable);
        model.addAttribute("page", page);
        return "/admin/admin-menu";
    }

    @PostMapping("/admin/user-status/{id}")
    public String changeStatus(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user.getUserStatus().equals(UserStatus.ACTIVE)) {
            user.setUserStatus(UserStatus.BLOCKED);
        } else {
            user.setUserStatus(UserStatus.ACTIVE);
        }
        userService.updateUser(user);
        return "redirect:/admin/menu";
    }

    @GetMapping("/admin/user-details/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("tariffs", user.getUsersTariffs());
        model.addAttribute("user", user);
        return "/client-menu";
    }
}
