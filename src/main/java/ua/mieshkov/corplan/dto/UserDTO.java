package ua.mieshkov.corplan.dto;

import lombok.*;
import ua.mieshkov.corplan.model.Role;
import ua.mieshkov.corplan.model.Tariff;
import ua.mieshkov.corplan.model.UserStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDTO {

    private Long id;

    @Pattern(regexp = "^[A-Z][a-zA-Z]{2,}(?: [A-Z][a-zA-Z]*){0,2}$", message = "{warn.incorrect.user.name.lat}")
    private String nameEn;

    @Pattern(regexp = "[[А-ЯҐІЇЄ]&&[^ЁЫЭЪ]][[а-яґєії\\']&&[^ёыэъ]]{2,}" +
                      "(?: [[А-ЯҐІЇЄ]&&[^ЁЫЭЪ]][[а-яґєії\\']&&[^ёыэъ]]*){0,2}$",
             message = "{warn.incorrect.user.name.ukr}")
    private String nameUkr;

    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$" +
                      "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$" +
                      "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$",
                    message ="{warn.incorrect.phone} ")
    private String phone;

    @Pattern(regexp = "[A-Za-z0-9_@]{6,20}", message = "{warn.incorrect.user.password}")
    private String password;

    @Pattern(regexp = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$",
            message = "{warn.incorrect.user.email}")
    private String email;

    @NotBlank(message = "{address.not_empty}")
    private String address;
    private Role role;
    private UserStatus userStatus;
    private List<Tariff> usersTariffs;
}
