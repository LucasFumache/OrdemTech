package br.com.usandoapi.ordemtech.DAO;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.usandoapi.ordemtech.model.OrdemTech;

@Repository
public interface IOrdemTech extends CrudRepository<OrdemTech, Integer> {
    Optional<OrdemTech> findByCpf(String cpf);
    
    
}
