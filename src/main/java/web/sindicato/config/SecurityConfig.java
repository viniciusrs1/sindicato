package web.sindicato.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		// Qualquer um pode fazer requisições para essas URLs
		//.antMatchers("/css/**", "/js/**", "/images/**", "/", "/index.html").permitAll()
		.antMatchers("/css/**", "/js/**", "/images/**").permitAll()
		// Um usuário autenticado e com o papel ADMIN pode fazer requisições para essas URLs	
		.antMatchers("/listusers").hasRole("ADMIN")
		.antMatchers("/addusers").hasRole("ADMIN")
		//.antMatchers("URL").hasAnyRole("ADMIN", "USUARIO")
		// Todas as outras requisições exigem um usuário autenticado
		.anyRequest().authenticated()
		.and()
		// A autenticação usando formulário está habilitada
		.formLogin()
			// Uma página de login customizada
			.loginPage("/login").permitAll()
			// Define a URL para o caso de falha no login
			//.failureUrl("/login-error");
			.and()
			// Para fazer logout (já é configurado automaticamente para a URL /logout)
			// Só está habilitado aqui para mudarmos a URL de sucesso do logout
		.logout()
			// Define a URL para o caso do usuário fazer logout. O padrão é /login
			.logoutSuccessUrl("/login");
    return http.build();

    }

}

