package me.dio.web.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import me.dio.web.controller.ViaCepService;
import me.dio.web.model.Usuario;
import me.dio.web.model.Endereco;
import me.dio.web.repository.EnderecoRepository;
import me.dio.web.repository.UsuarioRepository;
import me.dio.web.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	// Singleton: Injetar os componentes do Spring com @Autowired.
    @Autowired
    private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ViaCepService viaCepService;
	    
    @Autowired
    private PasswordEncoder encoder;
	
	// Strategy: Implementar os métodos definidos na interface.
	// Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

	@Override
	public Iterable<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario buscarPorId(Integer id) {
		// Buscar Cliente por ID.
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.get();
	}

	@Override
	public void inserir(Usuario usuario) {
		salvarClienteComCep(usuario);
	}

	@Override
	public void atualizar(Integer id, Usuario usuario) {
		// Buscar Cliente por ID, caso exista:
		Optional<Usuario> clienteBd = usuarioRepository.findById(id);
		if (clienteBd.isPresent()) {
			salvarClienteComCep(usuario);
		}
	}

	@Override
	public void deletar(Integer id) {
		// Deletar Cliente por ID.
		usuarioRepository.deleteById(id);
	}

	private void salvarClienteComCep(Usuario usuario) {
		// Verificar se o Endereco do Cliente já existe (pelo CEP).
		String cep = usuario.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
			// Caso não exista, integrar com o ViaCEP e persistir o retorno.
			Endereco novoEndereco = viaCepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		usuario.setEndereco(endereco);
		
		//criptografando antes de salvar no banco
        String pass = usuario.getPassword();
        usuario.setPassword(encoder.encode(pass));
        
		// Inserir Cliente, vinculando o Endereco (novo ou existente).
        usuarioRepository.save(usuario);
	}

}
