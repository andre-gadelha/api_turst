package com.teamTregamos.api_TurSt.cotroller;

import com.teamTregamos.api_TurSt.model.atividade.Atividade;
import com.teamTregamos.api_TurSt.model.atividade.AtividadeRepository;
import com.teamTregamos.api_TurSt.model.atividade.DadosCadastroAtividade;
import com.teamTregamos.api_TurSt.model.atividade.DadosEdicaoAtividade;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atividade")
@CrossOrigin(origins = "*")
public class AtividadeController {

    @Autowired
    private AtividadeRepository repository;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroAtividade atividade){

        repository.save(new Atividade(atividade));

    }

    @GetMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity getAllAtividades(){
        var allAtividades = repository.findAll();
        return ResponseEntity.ok(allAtividades);
    }

    @PutMapping
    @Transactional
    public void editar(@RequestBody @Valid DadosEdicaoAtividade atividade){

        var atividadeExistente = repository.getReferenceById(atividade.id());
        atividadeExistente.editarAtividade(atividade);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Integer id){

        repository.deleteById(id);
    }

}
