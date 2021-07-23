package com.cloud.mini.board.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cloud.mini.board.model.Complain;
import com.cloud.mini.board.model.User1;
import com.cloud.mini.board.repository.ComplainRepository;

@Controller
public class ComplainController {
	@Autowired
	ComplainRepository complainRepository;

	@Autowired
	HttpSession session;

	@GetMapping("/complain/writeC")
	public String complainWrite() {
		return "complain/writeC";
	}

	@PostMapping("/complain/writeC")
	public String complainWritePost(@ModelAttribute Complain complain) {
		User1 user = (User1) session.getAttribute("user_info");
		String userId = user.getEmail();
		complain.setUserId(userId);
	    complain.setCreateDate(new Date());
		complainRepository.save(complain);
		return "redirect:/board/";
	}
	
	@GetMapping("/complain/{id}")
	public String complainView(Model model, @PathVariable("id") long id) {
		Optional<Complain> data2 = complainRepository.findById(id);
		Complain complain = data2.get();
		model.addAttribute("complain", complain);
		return "complain/viewC";
	}
	

	

	@GetMapping("/complain")
	public String complain(Model model) {
		List<Complain> list2 = complainRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		model.addAttribute("list2", list2);
		return "board/list";
	}

	@GetMapping("/complain/updateC/{id}")
	public String eventUpdate(Model model, @PathVariable("id") long id) {
		Optional<Complain> data1 = complainRepository.findById(id);
		Complain complain = data1.get();
		model.addAttribute("complain", complain);
		return "complain/updateC";
	}

	@PostMapping("/complain/updateC/{id}")
	public String complainUpdatePost(@ModelAttribute Complain complain, @PathVariable("id") long id) {
		User1 user1 = (User1) session.getAttribute("user_info");
		String userId = user1.getEmail();
		complain.setUserId(userId);
		complain.setId(id);
	    complain.setCreateDate(new Date());
		complainRepository.save(complain);
		return "redirect:/board/";
	}

	@GetMapping("/complain/delete/{id}")
	public String complainDelete(@PathVariable("id") long id) {
		complainRepository.deleteById(id);
		return "redirect:/board/";
	}

}