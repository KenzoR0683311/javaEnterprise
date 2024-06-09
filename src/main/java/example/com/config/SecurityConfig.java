package example.com.config;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import example.com.views.LoginView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.core.annotation.Order;


import example.com.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends VaadinWebSecurity {
  @Autowired
  private UserService userService;
  @Autowired
  private JwtTokenFilter jwtTokenFilter;
    
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }
  
  

  @Bean
  @Order(1)
  SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
      .securityMatcher("/api/**")
      .authorizeHttpRequests(auth -> {
        auth.requestMatchers("/auth").permitAll();
        auth.anyRequest().authenticated();
      }).sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      //.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

      http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
      http.userDetailsService(userService);
      return http.build();
  }

  @Bean
  @Order(2)
  SecurityFilterChain soapSecurityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf().disable()
      .securityMatcher("/soap/**")
      .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

    return http.build();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    super.configure(http);
    setLoginView(http, LoginView.class);
  }
}

