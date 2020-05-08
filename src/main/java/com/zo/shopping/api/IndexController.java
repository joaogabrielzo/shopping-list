package com.zo.shopping.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

  @GetMapping
  public String index() {
    return "Api das Compras, pra ver certinho quando vai acabar as coisas";
  }
}
