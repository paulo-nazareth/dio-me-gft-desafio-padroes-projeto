package me.dio.web.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import me.dio.web.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
	
    @Query("SELECT e FROM Usuario e JOIN FETCH e.roles WHERE e.username= (:username)")
    public Usuario findByUsername(@Param("username") String username);

    boolean existsByUsername(String username);
}