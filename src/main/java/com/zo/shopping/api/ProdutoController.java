package com.zo.shopping.api;

import com.zo.shopping.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController {

  @Autowired
  ProdutoService service;

  @GetMapping
  public List<Produto> getProdutos() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity getProduto(@PathVariable("id") Long id) {
    Produto p = service.getById(id);

    return p.getClass() == Produto.class ?
           ResponseEntity.ok(p) :
           ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity insertProduto(@RequestBody Produto produto) {
    Produto p = service.insertProduto(produto);

    return ResponseEntity.created(URI.create("http://localhost:8080/v1/produtos" + p.getId())).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity updateProduto(@PathVariable("id") Long id, Produto produto) {
    Produto p = service.updateProduto(id, produto);

    return ResponseEntity.ok(p);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteProduto(@PathVariable("id") Long id) {
    Boolean deleted = service.deleteProduto(id);

    return deleted ?
           ResponseEntity.ok("Produto deletado") :
           ResponseEntity.badRequest().build();
  }
}
