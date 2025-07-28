package com.teamTregamos.api_TurSt.cotroller;

import com.teamTregamos.api_TurSt.model.atividade.Atividade;
import com.teamTregamos.api_TurSt.model.atividade.AtividadeRepository;
import com.teamTregamos.api_TurSt.model.atividade.DadosAtividade;
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
    public void cadastrar(@RequestBody @Valid DadosAtividade atividade){

        repository.save(new Atividade(atividade));
        //System.out.println(atividade);

    }

    @GetMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity getAllAtividades(){
        var allAtividades = repository.findAll();
        return ResponseEntity.ok(allAtividades);
    }
}
