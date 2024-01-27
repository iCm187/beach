package fr.hb.ibm.beach.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import fr.hb.ibm.beach.handler.CustomAuthenticationFailureHandler;
import fr.hb.ibm.beach.handler.CustomSuccessHandler;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).formLogin()
		//Handlers
				.successHandler(new CustomSuccessHandler())
				.failureHandler(new CustomAuthenticationFailureHandler())
				//Gestion de la deconnexion
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/index")
				//Gestion des acces
				.and()
				.authorizeHttpRequests(requests -> requests.antMatchers("/swagger-ui/index.html").permitAll()
						.antMatchers("/index").permitAll().antMatchers(HttpMethod.POST, "/reservation").hasRole("ADMIN")
						.antMatchers(HttpMethod.GET, "/reservations").hasAnyRole("USER", "ADMIN")
						.antMatchers("/client", "/clients").hasRole("ADMIN")
						.antMatchers("/tableauDeBord").hasAnyRole("ADMIN", "USER"))
				.headers().frameOptions().disable();
		
		return http.build();
	}
}
