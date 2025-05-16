package com.curso.resources;

import com.curso.domains.Technician;
import com.curso.domains.dtos.TechnicianDTO;
import com.curso.services.TechnicianService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/technician")
@Tag(name = "Técnico", description = "API para Gerenciamento de Técnicos")
public class TechnicianResource {

    @Autowired
    private TechnicianService techService;

    @GetMapping //exemplo: localhost:8080/technician
    @Operation(summary = "Listar todos os Técnicos",
            description = "Lista todos os Técnicos cadastrados")
    public ResponseEntity<List<TechnicianDTO>> findAll(){
        return ResponseEntity.ok().body(techService.findAll());

    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar um Técnico",
            description = "Busca por um Técnico através do seu Id")
    public ResponseEntity<TechnicianDTO> findById(@PathVariable Long id){
        Technician obj = this.techService.findbyId(id);
        return ResponseEntity.ok().body(new TechnicianDTO(obj));
    }

    @GetMapping(value = "/cpf/{cpf}")
    @Operation(summary = "Buscar um Técnico por CPF",
            description = "Busca por um Técnico através do seu CPF")
    public ResponseEntity<TechnicianDTO> findByCpf(@PathVariable String cpf){
        Technician obj = this.techService.findbyCpf(cpf);
        return ResponseEntity.ok().body(new TechnicianDTO(obj));
    }

    @GetMapping(value = "/email/{email}")
    @Operation(summary = "Buscar um Técnico por Email",
            description = "Busca por um Técnico através do seu Email")
    public ResponseEntity<TechnicianDTO> findByEmail(@PathVariable String email){
        Technician obj = this.techService.findbyEmail(email);
        return ResponseEntity.ok().body(new TechnicianDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Adicionar um Técnico",
            description = "Cadastra um novo Técnico")
    public ResponseEntity<TechnicianDTO> create(@Valid @RequestBody TechnicianDTO dto){
        Technician technician = techService.create(dto);
        //Cria a URI para o recurso criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(technician.getId()).toUri();
        //Retorna a resposta com o status 201 Created e o local do recurso criado
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Alterar Técnico",
            description = "Altera informações de um Técnico")
    public ResponseEntity<TechnicianDTO> update(@PathVariable Long id, @Valid @RequestBody TechnicianDTO objDto){
        Technician Obj = techService.update(id,objDto);
        return ResponseEntity.ok().body(new TechnicianDTO(Obj));

    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar Técnico",
            description = "Deleta um Técnico pelo seu Id")
    public ResponseEntity<TechnicianDTO> delete(@PathVariable Long id){
        techService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
