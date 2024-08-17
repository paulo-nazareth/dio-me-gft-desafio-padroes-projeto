package me.dio.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import me.dio.web.dtos.Login;
import me.dio.web.dtos.Sessao;
import me.dio.web.model.Usuario;
import me.dio.web.repository.UsuarioRepository;
import me.dio.web.security.JWTCreator;
import me.dio.web.security.JWTObject;
import me.dio.web.security.SecurityConfig;

@RestController
public class LoginController {
	
    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private SecurityConfig securityConfig;
    
    @Autowired
    private UsuarioRepository repository;
    
    @PostMapping("/login")
    public Sessao logar(@RequestBody Login login){
    	
        Usuario usuario = repository.findByUsername(login.getUsername());
        if(usuario!=null) {
            boolean passwordOk =  encoder.matches(login.getPassword(), usuario.getPassword());
            if (!passwordOk) {
                throw new RuntimeException("Senha inválida para o login: " + login.getUsername());
            }
            //Estamos enviando um objeto Sessão para retornar mais informações do usuário
            Sessao sessao = new Sessao();
            sessao.setLogin(usuario.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
            jwtObject.setRoles(usuario.getRoles());
            sessao.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return sessao;
        } else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }

}

