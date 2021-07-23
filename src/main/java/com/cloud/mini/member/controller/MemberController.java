package com.cloud.mini.member.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.mini.member.model.Admin;
import com.cloud.mini.member.model.Member;
import com.cloud.mini.member.repository.AdminRepository;
import com.cloud.mini.member.repository.MemberRepository;

@Controller
public class MemberController {

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	AdminRepository adminRepository;
	
	// 회원가입 페이지
	@GetMapping("/member/signup")
	public String Signup() {
		return "member/signup";
	}
	
	// 회원가입 처리
	@RequestMapping(value="/member/signup" , method = RequestMethod.POST)
	public String UserSignup(Model model,Member member,Admin admin,String id, String nickname, String phone) {
		Member Id = memberRepository.findAllById(id);
		Member Nick = memberRepository.findAllByNickname(nickname);
		Member PNum = memberRepository.findAllByPhone(phone);
		int role = member.getRole();
		System.out.println(role);
		if(role == 1) {
			if(Id != null || Nick != null || PNum != null) {
				return "/member/signup";
			}
			else if (Id == null && Nick == null && PNum ==null) {
				adminRepository.save(admin);
				return "redirect:/member/login";
			}
		}else{
			if(Id != null || Nick != null || PNum != null) {
				return "/member/signup";
			}
			else if (Id == null && Nick == null && PNum ==null) {
				memberRepository.save(member);
				return "redirect:/member/login";
			}
		}
		return "redirect:/";
	}
	
	
	//로그인 페이지
	@GetMapping("/member/login")
	public String Login() {
		return "member/login";
	}
	@PostMapping("/member/login")
	public String Login(Model model, @ModelAttribute Member member, HttpSession session) {
		Member dbMember = memberRepository.findByIdAndPwdAndRole(member.getId(), member.getPwd(),member.getRole());
		List<Member> members = memberRepository.findAll();
		List<Admin> admins = adminRepository.findAll();
		System.out.println(admins);
		if(dbMember != null) { //존재한다면 
			session.setAttribute("user", dbMember.getId()); //세션이 유지되는 동안 저장된다.
			System.out.println(session.getAttribute("user"));
			if(dbMember.getRole()==0) {
				model.addAttribute("admins",admins);
				model.addAttribute("members",members); //view에 저장할 모델  키 값 설정한다.
				return "user/admin";
			}else if(dbMember.getRole()==1) {
				model.addAttribute("members",dbMember);
				 return "user/UserInfo";
			}else{
				 return "user/MerInfo";
			}
		}
			
		return "/";	
}
	//로그 아웃 페이지
	@RequestMapping(value="member/logout" , method = RequestMethod.GET)
	public String logout(HttpSession session) {
		System.out.println(session.getAttribute("user"));
		if(session.getAttribute("user") != null) {
			session.setAttribute("user", "");
			return "member/logout";
		}else {
		return "member/denied";
		}
	}
	
	@PostMapping("/member/delete")
    public String disableMember(@RequestParam Map<String, String> disable) {
			
        System.out.println(disable);
        return "redirect:/user/admin";
    }
	
	// 내 정보(사용자) 페이지
//	@GetMapping("/user/info/{role}")
//	public String UserInfo(Model model, @PathVariable("role") int role) {
//			 Member users = memberRepository.findByRole(role);
//			 System.out.println(users);
//			 Member members = (Member) session.getAttribute("user_info");
//			 System.out.println(users.getId().equals(members.getId()));
//			 switch (role) {
//			 case 0:
//				 if(users.getId().equals(members.getId())){
//					 return "/user/admin.html";
//				 }
//				 break;
//			case 1:
//				 if(users.getId().equals(members.getId())) {
//					 return "/user/UserInfo";
//				 }
//				break;
//			case 2:
//				if(users.getId().equals(members.getId())){
//					return "/user/MerInfo";
//				}
//			default:
//				break;
//			}
//			
//
//		return "/";
//	}
//	 @GetMapping("/info")
//	    public String myPage(HttpSession httpSession, Model model) throws Exception {
//
//	        if(httpSession.getAttribute("id") != null) {
//	            Integer id = (int) httpSession.getAttribute("id");
//	            List<Map<String, Object>> marketList =  memberRepository.findAllById("id");
//	            List<Map<String, Object>> wish = memberService.wish(id);
//
//	            model.addAttribute("marketList", marketList);
//	            model.addAttribute("wish", wish);
//
//	            return "/member/myinfo";
//	        } else {
//	            return "redirect:/member/login";
//	        }
//	    }

	
//	@PostMapping("/user/info/{role}")
//	public String UserInfo(@ModelAttribute Member member, @PathVariable ("role") int role) {
//    	   Member admin = adminRepository.findByIdAndPwdAndRole(member.getId(),member.getPwd(),member.getRole());
//    	   System.out.println(admin);
//    	   if(admin != null) {
//    		   session.setAttribute("users", users);
//    	   }
//    	   return "header";
//		return "redirect:/user/userInfo/" + role; 
//	}
	@ResponseBody
	@RequestMapping(value="/member/id" , method=RequestMethod.POST)
	public int idCheck(String id) {
						Member email = memberRepository.findAllById(id);
						System.out.println(email);
						if(email == null) {
							return 0;
						}
						else {
							return 1;
						}
	}
	@ResponseBody
	@RequestMapping(value="/member/phoneCheck" , method=RequestMethod.POST)
	public int phone_numberCheck(String phone) {
						Member phone_num = memberRepository.findAllByPhone(phone);
						System.out.println(phone_num);
						if(phone_num == null) {
							return 0;
						}
						else {
							return 1;
						}
	}
	@ResponseBody
	@RequestMapping(value="/member/nickCheck" , method=RequestMethod.POST)
	public int nicknameCheck(String nickname) {
						Member nick = memberRepository.findAllByNickname(nickname);
						System.out.println(nick);
						if(nick == null) {
							return 0;
						}
						else {
							return 1;
						}
	}
}
