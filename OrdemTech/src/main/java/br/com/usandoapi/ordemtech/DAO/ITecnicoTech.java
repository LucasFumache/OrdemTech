package br.com.usandoapi.ordemtech.DAO;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.usandoapi.ordemtech.model.TecnicoTech;
@Repository
public interface ITecnicoTech extends JpaRepository<TecnicoTech, Integer> {
    Optional<TecnicoTech> findByCpf(String cpf);
    
}
