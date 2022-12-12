package web.sindicato.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private DataSource dataSource;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		// Qualquer um pode fazer requisições para essas URLs
		//.antMatchers("/css/**", "/js/**", "/images/**", "/", "/index.html").permitAll()
		.antMatchers("/css/**", "/js/**", "/images/**").permitAll()
		// Um usuário autenticado e com o papel ADMIN pode fazer requisições para essas URLs	
		.antMatchers("/usuarios/**").hasRole("ADMIN")
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
			.defaultSuccessUrl("/")
			.and()
			// Para fazer logout (já é configurado automaticamente para a URL /logout)
			// Só está habilitado aqui para mudarmos a URL de sucesso do logout
		.logout()
			// Define a URL para o caso do usuário fazer logout. O padrão é /login
			.logoutSuccessUrl("/login");
    return http.build();

    }
    
    @Bean
 	public UserDetailsService userDetailsService() {    	
          JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
          manager.setUsersByUsernameQuery("select nome_usuario, senha, ativo "
        		  						+ "from usuarios "
        		  						+ "where nome_usuario = ?");
          manager.setAuthoritiesByUsernameQuery(
        		  "SELECT tab.nome_usuario , papel.nome FROM"
        		+ "(SELECT usuarios.nome_usuario , usuarios.codigo FROM usuarios WHERE nome_usuario = ?) as tab "
        		+ " INNER JOIN usuario_papel ON codigo_usuario = tab.codigo "
        		+ " INNER JOIN papel ON codigo_papel = papel.codigo;");
          return manager;
	}
    
	@Bean
	public PasswordEncoder passwordEncoder() {
//		Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
//		return passwordEncoder;
		
		// Usar um PasswordEncoder que aceite todos os esquemas disponiveis no Spring Security
		// mas escolhendo quais vamos aceitar. As senhas comecam dizendo qual o esquema usado {noop}, {bcrypt}, {argon2}
		String idEnconder = "argon2";
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("argon2", new Argon2PasswordEncoder());
		encoders.put("noop", NoOpPasswordEncoder.getInstance());
//		encoders.put("bcrypt", new BCryptPasswordEncoder());
//		encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
//		encoders.put("scrypt", new SCryptPasswordEncoder());
//		encoders.put("sha256", new StandardPasswordEncoder());
		PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idEnconder, encoders);
		return passwordEncoder;
	}

}

