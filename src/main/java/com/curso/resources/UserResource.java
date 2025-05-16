package com.curso.resources;

import com.curso.domains.User;
import com.curso.domains.dtos.UserDTO;
import com.curso.services.UserService;
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
@RequestMapping(value = "/user")
@Tag(name = "Usuário", description = "API para Gerenciamento de Usuários")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping //exemplo: localhost:8080/user
    @Operation(summary = "Listar todos os Usuários",
            description = "Lista todos os Usuários cadastrados")
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok().body(userService.findAll());

    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar um Usuário",
            description = "Busca por um Usuário através do seu Id")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        User obj = this.userService.findbyId(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @GetMapping(value = "/cpf/{cpf}")
    @Operation(summary = "Buscar um Usuário por CPF",
            description = "Busca por um Usuário através do seu CPF")
    public ResponseEntity<UserDTO> findByCpf(@PathVariable String cpf){
        User obj = this.userService.findbyCpf(cpf);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @GetMapping(value = "/email/{email}")
    @Operation(summary = "Buscar um Usuário por Email",
            description = "Busca por um Usuário através do seu Email")
    public ResponseEntity<UserDTO> findByEmail(@PathVariable String email){
        User obj = this.userService.findbyEmail(email);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Adicionar um Usuário",
            description = "Cadastra um novo Usuário")
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO dto){
        User user = userService.create(dto);
        //Cria a URI para o recurso criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        //Retorna a resposta com o status 201 Created e o local do recurso criado
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Alterar Usuário",
            description = "Altera informações de um Usuário")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserDTO objDto){
        User Obj = userService.update(id,objDto);
        return ResponseEntity.ok().body(new UserDTO(Obj));

    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar Usuário",
            description = "Deleta um Usuário pelo seu Id")
    public ResponseEntity<UserDTO> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
