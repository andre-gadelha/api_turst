package com.teamTregamos.api_TurSt.controller;

import com.teamTregamos.api_TurSt.dto.DadosCadastroAtividade;
import com.teamTregamos.api_TurSt.dto.DadosDetalheAtividade;
import com.teamTregamos.api_TurSt.dto.DadosEdicaoAtividade;
import com.teamTregamos.api_TurSt.model.Atividade;
import com.teamTregamos.api_TurSt.repository.AtividadeRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/atividade")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearer-key")
public class AtividadeController {

    @Autowired
    private AtividadeRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroAtividade requestAtividade, UriComponentsBuilder uriBuilder){
        //cria uma nova atividade com os dados recebidos na requisição
        var atividade = new Atividade(requestAtividade);
        //salva a nova atividade no banco de dados
        repository.save(atividade);
        //constrói a URI para acessar a nova atividade criada
        var uri = uriBuilder.path("/atividade/{id}").buildAndExpand(atividade.getId()).toUri();
        //retorna uma resposta HTTP 201 (Created) com a URI da nova atividade no cabeçalho e o objeto atividade no corpo
        return ResponseEntity.created(uri).body(atividade);
    }

    @GetMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity listar(){
        var allAtividades = repository.findAll();
        return ResponseEntity.ok(allAtividades);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Integer id){
        //busca a atividade no banco de dados pelo ID fornecido
        //var atividade = repository.findById(id);
        var atividade = repository.getReferenceById(id);
        //retorna a atividade encontrada como resposta HTTP 200 (OK)
        return ResponseEntity.ok(new DadosDetalheAtividade(atividade));
    }

    @PutMapping
    @Transactional
    public ResponseEntity editar(@RequestBody @Valid DadosEdicaoAtividade atividade){

        //busca a atividade no banco de dados pelo ID fornecido
        var atividadeExistente = repository.getReferenceById(atividade.id());
        var descricaoAntiga = atividadeExistente.getDescricao();
        atividadeExistente.editarAtividade(atividade);// chama o método para editar a atividade

        return ResponseEntity.ok("Atividade '" + atividadeExistente.getId() + " - " + atividadeExistente.getNome() + "' editada com sucesso!\n" +
                        "Descrição antiga: " + descricaoAntiga + "\n" +
                        "Nova decrição: " + atividadeExistente.getDescricao());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Integer id){

        //deleta a atividade do banco de dados pelo ID fornecido
        var atividade = repository.getReferenceById(id);
        repository.delete(atividade);
        //retorna uma resposta HTTP 204 (No Content) indicando que a exclusão foi bem-sucedida
        return ResponseEntity.ok("Atividade '" + atividade.getId() + " - " + atividade.getNome() + "' excluída com sucesso!");
    }
}
