package th.mfu.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import th.mfu.Service.UserService;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    
    @Autowired
	private UserService userService;
    // Bean for Password Encoder
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // Bean for Authentication Provider
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }


// Security Filter Chain Configuration
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        // Authorize Requests Configuration
        .authorizeHttpRequests((authz) -> authz
            .requestMatchers("/registration**", "/js/**", "/css/**", "/img/**","/user-registration**","/rest-registration**")
            .permitAll() // Allow access without authentication to specified URL
            .anyRequest().authenticated()) // Require authentication for any other URL

        // Form Login Configuration
        .formLogin(formLogin ->
            formLogin
                .loginPage("/login") // Set custom login page URL
                .defaultSuccessUrl("/") // Set default success URL after login
                .permitAll()) // Allow access to login page without authentication

        // Logout Configuration
        .logout(logout ->
            logout
                .invalidateHttpSession(true) // Invalidate the HTTP session on logout
                .clearAuthentication(true) // Clear authentication on logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Set the URL for logout
                .logoutSuccessUrl("/login?logout") // Redirect to login page with logout parameter
                .permitAll()); // Allow access to the logout URL without authentication

    // Build and return the configured http object
    return http.build();
}


   
}
