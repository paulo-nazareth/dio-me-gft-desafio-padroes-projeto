package me.dio.web.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import me.dio.web.model.Endereco;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, String> {

}