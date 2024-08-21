package tz.ac.iact.va.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tz.ac.iact.va.security.AccessDeniedHandlerImp;
import tz.ac.iact.va.security.AuthEntryPointJwt;
import tz.ac.iact.va.security.CustomAuthenticationProvider;
import tz.ac.iact.va.security.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthFilter authFilter;

    private final AuthEntryPointJwt authEntryPointJwt;

    private final AccessDeniedHandlerImp accessDeniedHandlerImp;

    public SecurityConfiguration(JwtAuthFilter authFilter, AuthEntryPointJwt authEntryPointJwt, AccessDeniedHandlerImp accessDeniedHandlerImp) {
        this.authFilter = authFilter;
        this.authEntryPointJwt = authEntryPointJwt;
        this.accessDeniedHandlerImp = accessDeniedHandlerImp;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorization -> {
            authorization.requestMatchers("/auth/login","/api/v1/api-docs/**","/swagger-ui/*").permitAll().
                    anyRequest().authenticated();
        });
        http.sessionManagement(sess -> sess
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No sessions
        );
        http.exceptionHandling(handler -> {
          handler.accessDeniedHandler(accessDeniedHandlerImp);
          handler.authenticationEntryPoint(authEntryPointJwt);
        });
        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder) {

//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder);

        CustomAuthenticationProvider authenticationProvider=new CustomAuthenticationProvider(userDetailsService,passwordEncoder);

        return new ProviderManager(authenticationProvider);

    }





}
