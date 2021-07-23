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

import com.cloud.mini.board.model.Board;
import com.cloud.mini.board.model.Complain;
import com.cloud.mini.board.model.Event;
import com.cloud.mini.board.model.User1;
import com.cloud.mini.board.repository.BoardRepository;
import com.cloud.mini.board.repository.ComplainRepository;
import com.cloud.mini.board.repository.EventRepository;

@Controller
public class EventController {
	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	ComplainRepository complainRepository;
	
	@Autowired
	HttpSession session;

	@GetMapping("/event/writeE")
	public String eventWrite() {
		return "event/writeE";
	}

	@PostMapping("/event/writeE")
	public String eventWritePost(@ModelAttribute Event event) {
		User1 user = (User1) session.getAttribute("user_info");
		String userId = user.getEmail();
		event.setUserId(userId);
	    event.setCreateDate(new Date());
		eventRepository.save(event);
		return "redirect:/board/";
	}
	
	@GetMapping("/event/{id}")
	public String eventView(Model model, @PathVariable("id") long id) {
		Optional<Event> data1 = eventRepository.findById(id);
		Event event = data1.get();
		model.addAttribute("event", event);
		return "event/viewE";
	}
	

	

	@GetMapping("/event")
	public String event(Model model) {
		
		List<Event> list1 = eventRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		model.addAttribute("list1", list1);

		return "board/list";
	}

	@GetMapping("/event/updateE/{id}")
	public String eventUpdate(Model model, @PathVariable("id") long id) {
		Optional<Event> data = eventRepository.findById(id);
		Event event = data.get();
		model.addAttribute("event", event);
		return "event/updateE";
	}

	@PostMapping("/event/updateE/{id}")
	public String eventUpdatePost(@ModelAttribute Event event, @PathVariable("id") long id) {
		User1 user = (User1) session.getAttribute("user_info");
		String userId = user.getEmail();
		event.setUserId(userId);
		event.setId(id);
	    event.setCreateDate(new Date());
		eventRepository.save(event);
		return "redirect:/board/";
	}

	@GetMapping("/event/delete/{id}")
	public String eventDelete(@PathVariable("id") long id) {
		eventRepository.deleteById(id);
		return "redirect:/board/";
	}

}