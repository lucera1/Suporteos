package com.curso.resources;

import com.curso.domains.ServiceOrder;
import com.curso.domains.dtos.ServiceOrderDTO;
import com.curso.services.ServiceOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/serviceorder")
@Tag(name = "Service Order", description = "API para Gerenciamento de Ordens de Serviço")
public class ServiceOrderResource {

    @Autowired
    private ServiceOrderService osService;

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar uma Ordem de Serviço",
            description = "Busca uma Ordem de Serviço pelo seu Id")
    public ResponseEntity<ServiceOrderDTO> findbyId(@PathVariable UUID id){
        ServiceOrder obj = this.osService.findbyId(id);
        return ResponseEntity.ok().body(new ServiceOrderDTO(obj));
    }

    @GetMapping
    @Operation(summary = "Listar todas as Ordens de Serviço",
            description = "Retorna uma lista com todas as Ordens de Serviços cadastradas")
    public ResponseEntity<List<ServiceOrderDTO>> findAll(){
        return ResponseEntity.ok().body(osService.findAll());
    }

    @PostMapping
    @Operation(summary = "Criar Ordem de Serviço",
            description = "Cria uma nova Ordem de Serviço")
    public ResponseEntity<ServiceOrderDTO> create(@Valid @RequestBody ServiceOrderDTO objDto){
        ServiceOrder newObj = osService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Alterar Ordem de Serviço",
            description = "Altera uma Ordem de Serviço cadastrada pelo seu Id")
    public ResponseEntity<ServiceOrderDTO> update(@PathVariable UUID id, @Valid @RequestBody ServiceOrderDTO objDto){
        ServiceOrder Obj = osService.update(id, objDto);
        return ResponseEntity.ok().body(new ServiceOrderDTO(Obj));
    }
}
