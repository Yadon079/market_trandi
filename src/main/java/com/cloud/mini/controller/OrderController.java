package com.cloud.mini.controller;

import com.cloud.mini.model.Item;
import com.cloud.mini.model.Order;
import com.cloud.mini.repository.ItemRepository;
import com.cloud.mini.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class OrderController {

  @Autowired
  OrderRepository orderRepository;

  @Autowired
  ItemRepository itemRepository;

  @GetMapping("/order")
  public String order(Model model, int id) {
    Optional<Item> opt = itemRepository.findById(id);

    if (opt.isPresent()) {
      model.addAttribute("itemInfo", opt.get());
    }

    return "order";
  }

  @PostMapping("/order")
  public String orderPost(@ModelAttribute Order order, HttpSession session) {
    orderRepository.save(order);

    /*Iterator<String> iter = mRequest.getFileNames();

    while (iter.hasNext()) {
      String name = iter.next();
      System.out.println(name);

      List<MultipartFile> mFile = mRequest.getFiles("file");
      for (MultipartFile f : mFile) {
        long time = System.currentTimeMillis();

        String fName = f.getOriginalFilename();

        // String -> subString, replace, indexOf, split
        String prefix = fName.substring(0, fName.indexOf("."));
        String suffix = fName.substring(fName.indexOf("."));
        fName = prefix + "_" + time + suffix;

        try {
          String aPath = new File("").getAbsolutePath();
          // aPath + target\m2e-wtp\web-resources + fName
          f.transferTo(new File("./" + fName));
        } catch (IllegalStateException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }

    }*/
    return "redirect:/list";
  }
}
