package me.dio.web.service;

import org.springframework.stereotype.Service;

import me.dio.web.model.Usuario;

@Service
public interface UsuarioService {

	Iterable<Usuario> buscarTodos();

	Usuario buscarPorId(Integer id);

	void inserir(Usuario usuario);

	void atualizar(Integer id, Usuario usuario);

	void deletar(Integer id);

}