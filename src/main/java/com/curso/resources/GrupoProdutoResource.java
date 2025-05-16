package com.curso.resources;

import com.curso.domains.GrupoProduto;
import com.curso.domains.Produto;
import com.curso.domains.dtos.GrupoProdutoDTO;
import com.curso.domains.dtos.ProdutoDTO;
import com.curso.services.GrupoProdutoService;
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
@RequestMapping(value = "/grupoproduto")
@Tag(name = "Grupo de Produto", description = "API para Gerenciamento de Grupo de Produtos")
public class GrupoProdutoResource {

    @Autowired
    private GrupoProdutoService grupoProdutoService;

    @GetMapping //exemplo: http//localhost:8080/grupoproduto
    @Operation (summary = "Listar todos os grupos de produtos",
            description = "Retorna uma lista com todos os grupos de produtos cadastrados")
    public ResponseEntity<List<GrupoProdutoDTO>>findAll(){
        return ResponseEntity.ok().body(grupoProdutoService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation (summary = "Busca um grupo de produto por Id",
            description = "Realiza a busca de um grupo de produto cadastrado por Id")
    public ResponseEntity<GrupoProdutoDTO> findById(@PathVariable Integer id){
        GrupoProduto obj = this.grupoProdutoService.findbyId(id);
        return ResponseEntity.ok().body(new GrupoProdutoDTO(obj));
    }

    @PostMapping
    @Operation (summary = "Criar um novo Grupo de Produtos",
            description = "Cria um novo grupo de produtos com base nos dados fornecidos")
    public ResponseEntity<GrupoProdutoDTO> create(@Valid @RequestBody GrupoProdutoDTO dto){
        GrupoProduto grupoProduto = grupoProdutoService.create(dto);
        //Cria a URI para o recurso criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(grupoProduto.getId()).toUri();
        //Retorna a resposta com o status 201 Created e o local do recurso criado
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation (summary = "Altera um grupo de produto",
            description = "Altera um grupo de produto existente pelo seu Id")
    public ResponseEntity<GrupoProdutoDTO> update(@PathVariable Integer id, @Valid @RequestBody GrupoProdutoDTO objDto){
        GrupoProduto Obj = grupoProdutoService.update(id,objDto);
        return ResponseEntity.ok().body(new GrupoProdutoDTO(Obj));

    }

    @DeleteMapping(value = "/{id}")
    @Operation (summary = "Deletar um grupo de produto",
            description = "Deleta um grupo de produto existente a partir do seu Id")
    public ResponseEntity<GrupoProdutoDTO> delete(@PathVariable Integer id){
        grupoProdutoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
