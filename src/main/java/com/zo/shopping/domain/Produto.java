package com.zo.shopping.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity(name = "shopping_list")
@Data
public class Produto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String    produto;
  private String    marca;
  private int       quantidade;
  private LocalDate dataCompra;
  @Column(nullable = true)
  private LocalDate      dataFinal;
  @Column(nullable = true)
  private LocalDate   finalEstimado;

}
