package com.openwebinars.spring.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.openwebinars.spring.security.JwtTokenProvider;


@Configuration
public class SecurityConfig {

    //////JWT
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http,
                                             PasswordEncoder passwordEncoder,
                                             UserDetailsService userDetailsService) throws Exception {

        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and().build();

    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeRequests()
                .requestMatchers("/auth/**").permitAll()
                .anyRequest()
                .authenticated();

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }






    //////







    //Construim un usuari amb nom usuari 1, username postman i password encriptat i configurem els seus permisos
    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsService = new InMemoryUserDetailsManager();
        UserDetails user1 = User.builder()
                .username("postman1")
                .password(this.passwordEncoder().encode("admin"))
                .authorities("read")
                .build();

        userDetailsService.createUser(user1);
        return userDetailsService;
    }

    //Encriptem les contrasenyes
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
