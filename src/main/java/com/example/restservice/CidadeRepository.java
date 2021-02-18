package com.example.restservice;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CidadeRepository extends CrudRepository<Cidade, Integer> {

	@Query("select c from Cidade c")
	  ArrayList<Cidade> getAllCidades();
	
	@Query("select c from Cidade c where c.ibge_id = ?1")
	  Cidade findByCodigoIbge(int ibge_id);
	
	@Query("select c from Cidade c where c.capital = 'true' order by c.name")
	  Iterable<Cidade> getCapitais();
	
	@Query("select count(1) from Cidade c where c.uf = ?1")
	  Iterable<Cidade> getQuantCidadesUf(String uf);
	
	@Query("select c.name from Cidade c where c.uf = ?1")
	  String getAllCidadesByUf(String uf);
	
	@Query("select count(1) from Cidade c")
	  Integer getQuantCidades();
	
	@Query("select count(distinct ?1) from Cidade c")
	  Integer getQuantItensPorColuna(String nomeColuna);
}