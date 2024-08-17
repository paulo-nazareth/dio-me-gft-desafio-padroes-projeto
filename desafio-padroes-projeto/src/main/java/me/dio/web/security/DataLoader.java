package me.dio.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import me.dio.web.model.Usuario;
import me.dio.web.repository.UsuarioRepository;

@Configuration
public class DataLoader {

    @Autowired
    private PasswordEncoder encoder;
	
    @Bean
    public CommandLineRunner loadData(UsuarioRepository repository) {
        return args -> {
        	Usuario admin = repository.findByUsername("admin");
        	if(admin==null) {
        		admin = new Usuario();
        		admin.setName("Administrador");
        		admin.setUsername("admin");
        		admin.setPassword("admin123");
        		admin.getRoles().add("USERS");
        		admin.getRoles().add("MANAGERS");
        		//criptografando antes de salvar no banco
                String pass = admin.getPassword();
                admin.setPassword(encoder.encode(pass));
        		repository.save(admin);
        	}
        };
    }
}