package com.zo.shopping.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

  List<Produto> findByProduto(String produto);

  List<Produto> findByMarca(String marca);
}
