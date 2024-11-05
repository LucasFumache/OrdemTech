package br.com.usandoapi.ordemtech.DAO;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.usandoapi.ordemtech.model.OrdemServico;


public interface IOrdemServicocli extends JpaRepository<OrdemServico, Integer> {
    List<OrdemServico> findByClienteId(Integer Id_cliente);
    List<OrdemServico> findByTecnicoId(Integer Id_tecnico);
	

}
