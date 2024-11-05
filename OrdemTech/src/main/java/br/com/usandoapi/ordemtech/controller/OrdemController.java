package br.com.usandoapi.ordemtech.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.usandoapi.ordemtech.DAO.IOrdemServicocli;
import br.com.usandoapi.ordemtech.DAO.IOrdemTech;
import br.com.usandoapi.ordemtech.DAO.ITecnicoTech;
import br.com.usandoapi.ordemtech.model.OrdemServico;
import br.com.usandoapi.ordemtech.model.OrdemTech;
import br.com.usandoapi.ordemtech.model.TecnicoTech;

@RestController
@CrossOrigin("*")
@RequestMapping("/ordem")
public class OrdemController {

    @Autowired
    private IOrdemTech dao;

    @Autowired
    private ITecnicoTech tecnicoDao;
    
    @Autowired
    private IOrdemServicocli ordemServicoRepository;

    @GetMapping("/cliente/{id_cliente}")
    public ResponseEntity<List<OrdemServico>> buscarOrdensPorCliente(@PathVariable Integer id_cliente) {
        List<OrdemServico> ordens = ordemServicoRepository.findByClienteId(id_cliente);

        if (!ordens.isEmpty()) {
            return ResponseEntity.ok(ordens);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/tecnico/{id_tecnico}")
    public ResponseEntity<List<OrdemServico>> buscarOrdensPorIdtecnico(@PathVariable Integer id_tecnico) {
        List<OrdemServico> ordens = ordemServicoRepository.findByTecnicoId(id_tecnico);

        if (!ordens.isEmpty()) {
            return ResponseEntity.ok(ordens);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    

    @GetMapping("/cliente/cpf/{cpf}")
    public ResponseEntity<OrdemTech> acharClientePorCpf(@PathVariable String cpf) {
        Optional<OrdemTech> clienteOptional = dao.findByCpf(cpf);

        if (clienteOptional.isPresent()) {
            return ResponseEntity.ok(clienteOptional.get());
            
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/cliente")
    public OrdemTech criarCliente(@RequestBody OrdemTech cliente) {
        return dao.save(cliente);
    }

    @PutMapping("/cliente/cpf/{cpf}")
    public ResponseEntity<OrdemTech> atualizarSenha(@PathVariable String cpf, @RequestBody String novaSenha) {
        Optional<OrdemTech> clienteOptional = dao.findByCpf(cpf);

        if (clienteOptional.isPresent()) {
            OrdemTech cliente = clienteOptional.get();
            cliente.setSenha(novaSenha); 
            dao.save(cliente);
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Integer id) {
        Optional<OrdemTech> clienteOptional = dao.findById(id);
        
        if (clienteOptional.isPresent()) {
            dao.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tecnico/cpf/{cpf}")
    public ResponseEntity<TecnicoTech> acharTecnicoPorCpf(@PathVariable String cpf) {
        Optional<TecnicoTech> tecnicoOptional = tecnicoDao.findByCpf(cpf);

        if (tecnicoOptional.isPresent()) {
            return ResponseEntity.ok(tecnicoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @PostMapping("/tecnico")
    public TecnicoTech criarTecnico(@RequestBody TecnicoTech tecnico) {
        return tecnicoDao.save(tecnico);
    }
    
    @PutMapping("/tecnico/cpf/{cpf}")
    public ResponseEntity<TecnicoTech> atualizarTecnico(@PathVariable String cpf, @RequestBody TecnicoTech tecnico) {
        Optional<TecnicoTech> tecnicoOptional = tecnicoDao.findByCpf(cpf);

        if (tecnicoOptional.isPresent()) {
            TecnicoTech tecnicoExistente = tecnicoOptional.get();
            tecnicoExistente.setNome(tecnico.getNome());
            tecnicoExistente.setTelefone(tecnico.getTelefone());
            tecnicoExistente.setEspecialidade(tecnico.getEspecialidade());
            tecnicoDao.save(tecnicoExistente);
            return ResponseEntity.ok(tecnicoExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/tecnico/{id}")
    public ResponseEntity<Void> excluirTecnico(@PathVariable Integer id) {
        Optional<TecnicoTech> tecnicoOptional = tecnicoDao.findById(id);

        if (tecnicoOptional.isPresent()) {
            tecnicoDao.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
}
