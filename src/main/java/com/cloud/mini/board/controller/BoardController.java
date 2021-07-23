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
import com.cloud.mini.board.model.Question;
import com.cloud.mini.board.model.User1;
import com.cloud.mini.board.repository.BoardRepository;
import com.cloud.mini.board.repository.ComplainRepository;
import com.cloud.mini.board.repository.EventRepository;
import com.cloud.mini.board.repository.QuestionRepository;
import com.cloud.mini.member.repository.MemberRepository;


@Controller
public class BoardController {
   @Autowired
   BoardRepository boardRepository;
   
   @Autowired
   EventRepository eventRepository;
   
   @Autowired
   ComplainRepository complainRepository;
   
   @Autowired
   QuestionRepository questionRepository;
   
   @Autowired
   MemberRepository memberRepository;
   
   @GetMapping("/board/write")
   public String boardWrite() {
      return "board/write";
   }

   @PostMapping("/board/write")
   public String boardWritePost(@ModelAttribute Board board, HttpSession session) {
	  String user = (String) session.getAttribute("user");
      board.setUserId(user);
      board.setCreateDate(new Date());
      boardRepository.save(board);
      return "redirect:/board/";
   }
   
   
   @GetMapping("/board/{id}")
   public String boardView(Model model, @PathVariable("id") long id) {
      Optional<Board> data = boardRepository.findById(id);
      Board board = data.get();
      model.addAttribute("board", board);
      return "board/view";
   }
   
   


   @GetMapping("/board")
   public String board(Model model) {	
	  
      List<Board> list = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
      List<Event> list1 = eventRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
      List<Complain> list2 = complainRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
      List<Question> list3 = questionRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
      model.addAttribute("list", list);
      model.addAttribute("list1", list1);
      model.addAttribute("list2", list2);
      model.addAttribute("list3", list3);
      return "board/list";
   }

   @GetMapping("/board/update/{id}")
   public String boardUpdate(Model model, @PathVariable("id") long id) {
      Optional<Board> data = boardRepository.findById(id);
      Board board = data.get();
      model.addAttribute("board", board);
      return "board/update";
   }

   @PostMapping("/board/update/{id}")
   public String boardUpdatePost(@ModelAttribute Board board, @ModelAttribute Event event,  @PathVariable("id") long id, HttpSession session) {
      String user = (String) session.getAttribute("user");
      board.setUserId(user);
      board.setCreateDate(new Date());
      board.setId(id);
      
      boardRepository.save(board);
      return "redirect:/board/";
   }

   @GetMapping("/board/delete/{id}")
   public String boardDelete(@PathVariable("id") long id) {
   boardRepository.deleteById(id);
   return "redirect:/board";
   }
   

}