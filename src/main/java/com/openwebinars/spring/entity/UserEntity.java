package com.openwebinars.spring.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;  // Import UserDetails
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//Seguretat JWT

@Entity
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    private String password;

    //Fem que l'email sigui unic
    @Column(unique = true)
    private String email;


    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<UserAuthority> authorities = new ArrayList<>();


    public UserEntity() {
    }

    public UserEntity(Long id, String username, String password, String email, List<UserAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.toString()))
                .toList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    //
}
