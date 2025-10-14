package sber.dad.libraryproject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import java.util.Arrays;
import java.util.List;

import static sber.dad.libraryproject.constants.UserRoleConstants.ADMIN;
import static sber.dad.libraryproject.constants.UserRoleConstants.LIBRARIAN;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final List<String> RESOURCES_WHITE_LIST = List.of(
            "/resources/**",
            "/js/**",
            "/css/**",
            "/swagger-ui/**",
            "/");
    private final List<String> BOOKS_WHITE_LIST = List.of("/books");

    private final List<String> BOOKS_PERMISSION_LIST = List.of(
            "/books/add",
            "/books/update",
            "/books/delete");
    private final List<String> USERS_WHITE_LIST = List.of("/login",
            "/users/registration",
            "/users/change-password",
            "/users/remember-password");

    //https://docs.spring.io/spring-security/reference/servlet/exploits/firewall.html
    @Bean
    public HttpFirewall httpFirewall() {

        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedPercent(true);
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowSemicolon(true);
        firewall.setAllowedHttpMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));

        return firewall;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
//                .csrf().disable() по дефолту csrf включен
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(BOOKS_PERMISSION_LIST.toArray(String[]::new)).hasAnyRole(ADMIN, LIBRARIAN)
                        .requestMatchers(BOOKS_WHITE_LIST.toArray(String[]::new)).permitAll()
                        .requestMatchers(USERS_WHITE_LIST.toArray(String[]::new)).permitAll()
                        .requestMatchers(RESOURCES_WHITE_LIST.toArray(String[]::new)).permitAll()
                        .anyRequest().authenticated())

                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll())
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll());
        return http.build();

    }
}

