package ru.kata.spring.boot_security.demo.model;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
//@Table(name = "users", schema = "spring_hiber")
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    @Size(min=2, message = "Не меньше 5 знаков")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Логин не должен содержать цифры, пробелы, спецсимволы")
    private String username;

    @Column(name = "name")
    @Size(min = 2, message = "Имя должно содержать как минимум 2 символа.")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Имя не должно содержать цифры, пробелы, спецсимволы")
    private String name;

    @Column(name = "lastname")
    @Size(min = 2, message = "Фамилия должна содержать как минимум 2 символа.")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Фамилия не должна содержать цифры, пробелы, спецсимволы")
    private String lastName;

    @Column(name = "email")
    @Email
    private String email;

    @Size(min = 2, message = "Не меньше 5 знаков")
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "roles_id")}
    )
    private Set<Role> roles;

    public User(String username, String name, String lastName, String email, String password, Set<Role> adminRole) {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

        this.roles = adminRole;

    }

    public User() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getRolesString() {
        return roles.stream().map(Role::getAuthority).collect(Collectors.joining(", "));
    }
}
