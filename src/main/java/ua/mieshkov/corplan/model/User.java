package ua.mieshkov.corplan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Simple Javabean domain object that represents User.
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="full_name_en")
    private String nameEn;

    @Column(name="full_name_ukr")
    private String nameUkr;

    @Column(name="phone_number")
    private String phone;

    private Double balance;

    @Enumerated(value = EnumType.STRING)
    @Column(name="role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name="status")
    private UserStatus userStatus;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="address")
    private String address;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_has_tariff",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tariff_id"))
    private Set<Tariff> usersTariffs = new HashSet<>();

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(nameEn, user.nameEn) &&
                Objects.equals(nameUkr, user.nameUkr) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(balance, user.balance) &&
                Objects.equals(email, user.email) &&
                Objects.equals(address, user.address) &&
                Objects.equals(password, user.password) &&
                role == user.role &&
                userStatus == user.userStatus &&
                Objects.equals(usersTariffs, user.usersTariffs);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nameEn='" + nameEn + '\'' +
                ", phone='" + phone + '\'' +
                ", balance=" + balance +
                ", email=" + email +
                ", address=" + address +
                ", password=" + password +
                ", role=" + role +
                ", userStatus=" + userStatus +
                '}';
    }

}
