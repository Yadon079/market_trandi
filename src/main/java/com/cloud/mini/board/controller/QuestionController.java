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
import com.cloud.mini.board.model.Question;
import com.cloud.mini.board.model.User1;
import com.cloud.mini.board.repository.QuestionRepository;

@Controller
public class QuestionController {
	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	HttpSession session;

	@GetMapping("/question/writeQ")
	public String questionWrite() {
		return "question/writeQ";
	}

	@PostMapping("/question/writeQ")
	public String questionWritePost(@ModelAttribute Question question) {
		User1 user = (User1) session.getAttribute("user_info");
		String userId = user.getEmail();
		question.setUserId(userId);
	    question.setCreateDate(new Date());
		questionRepository.save(question);
		return "redirect:/board/";
	}
	
	@GetMapping("/question/{id}")
	public String questionView(Model model, @PathVariable("id") long id) {
		Optional<Question> data3 = questionRepository.findById(id);
		Question question = data3.get();
		model.addAttribute("question", question);
		return "question/viewQ";
	}
	

	

	@GetMapping("/question")
	public String complain(Model model) {
		List<Question> list3 = questionRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		model.addAttribute("list3", list3);
		return "board/list";
	}

	@GetMapping("/question/updateQ/{id}")
	public String questionUpdate(Model model, @PathVariable("id") long id) {
		Optional<Question> data3 = questionRepository.findById(id);
		Question question = data3.get();
		model.addAttribute("question", question);
		return "question/updateQ";
	}

@PostMapping("/question/updateQ/{id}")
	public String questionUpdatePost(@ModelAttribute Question question, @PathVariable("id") long id) {
		User1 user = (User1) session.getAttribute("user_info");
		String userId = user.getEmail();
		question.setUserId(userId);
		question.setId(id);
	    question.setCreateDate(new Date());
		questionRepository.save(question);
		return "redirect:/board/";
	}

	@GetMapping("/question/delete/{id}")
	public String questionDelete(@PathVariable("id") long id) {
		questionRepository.deleteById(id);
		return "redirect:/board/";
	}

}