package com.nnk.springboot.domain;

import com.nnk.springboot.annotation.Unique;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The type User.
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username",unique = true)
    @NotBlank(message = "Username is mandatory")
    @Unique
    private String username;

    @Column(name = "password")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
            message = "Password must contain at least 1 uppercase char, 8 characters, 1 digit, and 1 symbol")
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Column(name = "full_name",unique = true)
    @NotBlank(message = "FullName is mandatory")
    @Unique
    private String fullName;

    @Column(name = "role")
    @NotBlank(message = "Role is mandatory")
    private String role;

    /**
     * Instantiates a new User.
     */
    public User() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(this.role));

        return grantedAuthorities;
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

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param fullName the full name
     * @param role     the role
     */
    public User(String username, String fullName, String role) {
        this.username = username;
        this.fullName = fullName;
        this.role = role;
    }
}
