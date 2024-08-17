package me.dio.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.dio.web.model.Endereco;
import me.dio.web.model.Usuario;
import me.dio.web.repository.UsuarioRepository;
import me.dio.web.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
    
    @Autowired
    private UsuarioRepository repository;

	@GetMapping
	public ResponseEntity<Iterable<Usuario>> buscarTodos() {
		return ResponseEntity.ok(usuarioService.buscarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(usuarioService.buscarPorId(id));
	}

	@PostMapping
	public ResponseEntity<Usuario> inserir(@RequestBody Usuario usuario) {
		usuarioService.inserir(usuario);
		return ResponseEntity.ok(usuario);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
		usuarioService.atualizar(id, usuario);
		return ResponseEntity.ok(usuario);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		usuarioService.deletar(id);
		return ResponseEntity.ok().build();
	}
	
}