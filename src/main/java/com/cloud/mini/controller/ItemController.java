package com.cloud.mini.controller;

import com.cloud.mini.model.Item;
import com.cloud.mini.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class ItemController {

  @Autowired
  ItemRepository itemRepository;

  @GetMapping("/item")
  public String item(Model model, int id) {
    Optional<Item> opt = itemRepository.findById(id);

    if (opt.isPresent()) {
      model.addAttribute("item", opt.get());
    }

    return "item";
  }

/*
    @PostMapping("/order")
    public
*/
}
