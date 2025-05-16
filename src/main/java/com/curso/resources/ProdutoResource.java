package com.curso.resources;

import com.curso.domains.GrupoProduto;
import com.curso.domains.Produto;
import com.curso.domains.dtos.GrupoProdutoDTO;
import com.curso.domains.dtos.ProdutoDTO;
import com.curso.services.ProdutoService;
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
@RequestMapping (value = "/produto")
@Tag(name = "Produto", description = "API para Gerenciamento de Produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping //exemplo: localhost:8080/produto
    @Operation(summary = "Listar todos os produtos",
            description = "Retorna uma lista com todos os produtos cadastrados")
    public ResponseEntity<List<ProdutoDTO>> findAll(){
        return ResponseEntity.ok().body(produtoService.findAll());

    }

    @GetMapping(value = "/{id}")
    @Operation (summary = "Busca um produto por Id",
            description = "Realiza a busca de um produto cadastrado por Id")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id){
        Produto obj = this.produtoService.findbyId(id);
        return ResponseEntity.ok().body(new ProdutoDTO(obj));
    }

    @GetMapping(value = "/codigobarra/{codigoBarra}")
    @Operation (summary = "Busca um produto por um Codigo de Barras",
            description = "Realiza a busca de um produto cadastrado por Codigo de Barras")
        public ResponseEntity<ProdutoDTO> findById(@PathVariable String codigoBarra){
            Produto obj = this.produtoService.findbyCodigoBarra(codigoBarra);
            return ResponseEntity.ok().body(new ProdutoDTO(obj));
    }

    @PostMapping
    @Operation (summary = "Adicionar um novo Produto",
            description = "Cadastra um novo Produto com base nos dados fornecidos")
    public ResponseEntity<ProdutoDTO> create(@Valid @RequestBody ProdutoDTO dto){
        Produto produto = produtoService.create(dto);
        //Cria a URI para o recurso criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(produto.getIdProduto()).toUri();
        //Retorna a resposta com o status 201 Created e o local do recurso criado
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation (summary = "Alterar um Produto",
            description = "Altera um produto ja cadastrado pelo seu Id")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @Valid @RequestBody ProdutoDTO objDto){
        Produto Obj = produtoService.update(id,objDto);
        return ResponseEntity.ok().body(new ProdutoDTO(Obj));

    }

    @DeleteMapping(value = "/{id}")
    @Operation (summary = "Deletar um produto",
            description = "Deleta um produto cadastrado pelo seu Id")
    public ResponseEntity<ProdutoDTO> delete(@PathVariable Long id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
