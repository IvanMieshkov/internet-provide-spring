package ua.mieshkov.corplan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple Javabean domain object that represents Tariff
 */

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tariff")
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name_ukr")
    private String nameUkr;

    @Column(name="name_en")
    private String nameEn;

    @Column(name="price")
    private Double price;

    @Column(name="service")
    private String service;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            mappedBy = "usersTariffs")
    private Set<User> tariffsUsers = new HashSet<>();

}


