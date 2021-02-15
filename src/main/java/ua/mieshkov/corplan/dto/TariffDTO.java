package ua.mieshkov.corplan.dto;

import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TariffDTO {

    private Long id;

    private String service;

    @Pattern(regexp = "[A-Za-z0-9_@]{1,20}", message = "{warn.incorrect.tariff.name.lat}")
    private String nameEn;

    @Pattern(regexp = "[[А-ЯҐІЇЄ]&&[^ЁЫЭЪ]][[а-яґєії\\']&&[^ёыэъ]]{2,}", message = "{warn.incorrect.tariff.name.ukr}")
    private String nameUkr;

    @Pattern(regexp = "^\\d+(.\\d{1,2})?$", message = "{warn.incorrect.tariff.price}")
    private String price;
}
