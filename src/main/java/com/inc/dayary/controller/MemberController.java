package com.inc.dayary.controller;

import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inc.dayary.domain.Member;
import com.inc.dayary.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/member/signup")
	public String signup(Model model) {
		model.addAttribute("member", new Member());
		return "member/signup";
	}
	@PostMapping("/member/signup")
	public String signup(@ModelAttribute @Valid Member member, BindingResult result, HttpSession session) {
		if(memberService.findOne(member.getId()) != null) {
			//중복확인 버튼 안 누르고 들어왔을 경우.
			result.addError(new FieldError("idError", "id", "중복된 아이디입니다."));
		}
		if(!member.isPasswordValid()) {
			//직접 validation 수행 및 에러를 result객체에 담는다.
			//멤버가 직접적으로 관련이 있을 경우 필요한 메서드를 해당 객체로 빼는 경우가 있다.
			FieldError error = new FieldError("passwordError", "passwordConfirm", "패스워드가 일치하지 않습니다.");
			result.addError(error);
		}
		
		if(!member.getEmail().equals((String)session.getAttribute("email"))) {
			//들어온 이메일이 인증받은 이메일과 다를 경우ㅡ.
			FieldError error = new FieldError("emailNotEqualsError", "email", "인증받은 메일로 가입해 주세요.");
			result.addError(error);
		}
		
		if(!member.getEmailCode().equals((String)session.getAttribute("emailCode"))){
			FieldError error = new FieldError("emailCodeError", "emailCode", "이메일 코드가 일치하지 않습니다.");
			result.addError(error);
		}
		
		if(result.hasErrors()) {
			return "member/signup";
		}
		
		memberService.signup(member);
		session.invalidate();
		
		return "redirect:/";
	}
	@PostMapping("/member/dupcheck")
	@ResponseBody
	public String dupcheck(@RequestBody String id) {
		Member member = memberService.findOne(id);
		if(member==null) {
			return "n";
		}else {
			return "y";
		}
	}
	
	@PostMapping("/member/emailcertify")
	@ResponseBody
	public String emailcertify(@RequestParam String email, HttpSession session) {
		if(email.length() == 0) {
			return "null";
		}
		
		if(!emailValidator(email)) {
			//이메일 잘못 입력했을경우
			return "incorrect";
		}
		if(memberService.emailDupCheck(email)) {
			//중복된 이메일을 입력했을 경우
			return "duplicated";
		}
		
		String emailCode = null;
		try {
			//컨트롤러에서 예외를 처리해준다.
			//이메일 보내는 작업, 이메일을 보내고 코드를 리턴받아 emailCode변수에 저장.
			//이메일 코드를 세션에 저장해서 가입완료 버튼 눌렀을 시 맞는지 비교할 것.
			emailCode = memberService.sendCertifyEmail(email);
		}catch(RuntimeException e ) {
			return "error";
		}
		session.setAttribute("email", email);
		session.setAttribute("emailCode", emailCode);
		
		return "success";
	}
	private boolean emailValidator(String email) {
		// 이메일 정규표현식 검사
		return Pattern.compile("([0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3})")
				.matcher(email).matches();
	}
}
