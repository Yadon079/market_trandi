package com.cloud.mini.controller;

import com.cloud.mini.model.Item;
import com.cloud.mini.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ItemListController {

  @Autowired
  ItemRepository itemRepository;

  @GetMapping("/list")
  private String itemList(Model model) {
    List<Item> itemList = itemRepository.findAll();
    model.addAttribute("list", itemList);
    return "list";
  }

}
