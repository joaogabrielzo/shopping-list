package com.zo.shopping.domain;

import lombok.Data;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.constraints.Null;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

  @Autowired
  ProdutoRepository repo;

  public Produto getById(Long id) {
    Optional<Produto> produtoList = repo.findById(id);

    return produtoList.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado.", "Não encontrado"));
  }

  public List<Produto> getAll() {
    return repo.findAll();
  }

  public List<Produto> getByProduto(String produto) {
    return repo.findByProduto(produto);
  }

  public List<Produto> getByMarca(String marca) {
    return repo.findByMarca(marca);
  }

  public Produto insertProduto(Produto produto) {
    Assert.isNull(produto.getId(), "Não foi possível inserir o produto.");

    LocalDate finalEstimado = estimarFinal(produto);
    produto.setFinalEstimado(finalEstimado);

    return repo.save(produto);
  }

  private LocalDate estimarFinal(Produto produto) {
    List<Produto> p = getByProduto(produto.getProduto());
    if (p.size() <= 2) return null;
    else {
      List<Integer> newProdutos =
        p.stream().map(item -> Period.between(item.getDataCompra(), item.getDataFinal()).getDays())
         .collect(Collectors.toList());

      Integer mediaEstimada = newProdutos.stream().mapToInt(Integer::intValue).sum();

      long mediaLong = Long.valueOf(mediaEstimada);

      return produto.getDataCompra().plusDays(mediaLong);
    }

  }


  public Produto updateProduto(Long id, Produto produto) {
    Produto p = getById(id);

    p.setProduto(produto.getProduto());
    p.setMarca(produto.getMarca());
    p.setQuantidade(produto.getQuantidade());
    p.setDataCompra(produto.getDataCompra());
    p.setDataFinal(produto.getDataFinal());
    repo.save(p);

    return p;
  }

  public Boolean deleteProduto(Long id) {
    repo.deleteById(id);

    return repo.findById(id).isEmpty();
  }

}
