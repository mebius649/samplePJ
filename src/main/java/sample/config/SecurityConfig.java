package sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
        	.headers(headers -> headers
        		.frameOptions(frame -> frame.deny())
        		)
        	
        	.sessionManagement(session -> session
        	    .sessionFixation().migrateSession()
        	        )
        	
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/signup", "/css/**").permitAll()
                .anyRequest().authenticated()
                
            )
            
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/tasks", false)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll()
            )
            .build();
    }
}